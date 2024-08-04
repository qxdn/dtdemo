package com.qxdn.dtdemo.action.impl;

import com.qxdn.dtdemo.action.OrderAction;
import com.qxdn.dtdemo.dal.model.Account;
import com.qxdn.dtdemo.dal.model.Order;
import com.qxdn.dtdemo.dal.repository.AccountRepository;
import com.qxdn.dtdemo.dal.repository.OrderRepository;
import io.seata.rm.tcc.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


@LocalTCC
@Service
@Slf4j
public class OrderActionImpl implements OrderAction {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;

    @TwoPhaseBusinessAction(name = "purchaseItem", commitMethod = "commit", rollbackMethod = "cancel")
    @Override
    public boolean purchaseItem(BusinessActionContext actionContext, @BusinessActionContextParameter("userId") Long userId,
                                @BusinessActionContextParameter("itemId") Long itemId,@BusinessActionContextParameter("count") Integer count) {
        log.info("开始尝试购买商品 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Account account = accountRepository.findAccountByUserId(userId);
        Long amount = calculateAmount(itemId, count);
        if (account.getMoney() < amount) {
            throw new RuntimeException("余额不足");
        }

        account.setTransactionalMoney(account.getTransactionalMoney()+amount);
        account.setMoney(account.getMoney()-amount);
        accountRepository.save(account);
        BusinessActionContextUtil.addContext("amount",amount);
        return true;
    }

    private Long calculateAmount(Long itemId, Integer count) {
        return 100L*count;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.info("开始提交购买商品 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Long userId =((Number) actionContext.getActionContext("userId")).longValue();
        Integer count = (Integer) actionContext.getActionContext("count");
        Long itemId = ((Number) actionContext.getActionContext("itemId")).longValue();
        Long amount = ((Number)  actionContext.getActionContext("amount")).longValue();
        Account account = accountRepository.findAccountByUserId(userId);
        account.setTransactionalMoney(account.getTransactionalMoney()-amount);
        accountRepository.save(account);

        Order order = new Order();
        order.setUserId(userId);
        order.setItemId(itemId);
        order.setCount(count);
        order.setAmount(amount);
        orderRepository.save(order);

        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext actionContext) {
        log.info("开始回滚购买商品 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Long userId =  ((Number) actionContext.getActionContext("userId")).longValue();
        Number amountNumber = ((Number) actionContext.getActionContext("amount"));
        if (Objects.isNull(amountNumber)){
            return true;
        }
        Long amount = amountNumber.longValue();
        Account account = accountRepository.findAccountByUserId(userId);
        account.setTransactionalMoney(account.getTransactionalMoney()-amount);
        account.setMoney(account.getMoney()+amount);
        accountRepository.save(account);
        return true;
    }
}
