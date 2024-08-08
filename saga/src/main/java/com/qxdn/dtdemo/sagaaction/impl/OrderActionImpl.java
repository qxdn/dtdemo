package com.qxdn.dtdemo.sagaaction.impl;

import com.qxdn.dtdemo.sagaaction.OrderAction;
import com.qxdn.dtdemo.dal.model.Account;
import com.qxdn.dtdemo.dal.model.Order;
import com.qxdn.dtdemo.dal.repository.AccountRepository;
import com.qxdn.dtdemo.dal.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;


@Slf4j
@Service("orderAction")
public class OrderActionImpl implements OrderAction {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountRepository accountRepository;


    @Override
    public boolean purchaseItem(String businessKey, Long userId, Long itemId, Integer count, Map<String, Object> params) {
        log.info("开始尝试购买商品 businessKey={}",businessKey);
        Account account = accountRepository.findAccountByUserId(userId);
        Long amount = calculateAmount(itemId, count);
        if (account.getMoney() < amount) {
            throw new RuntimeException("余额不足");
        }

        account.setMoney(account.getMoney()-amount);
        accountRepository.save(account);
        Order order = new Order();
        order.setItemId(itemId);
        order.setUserId(userId);
        order.setCount(count);
        order.setAmount(amount);
        orderRepository.save(order);
        params.put("orderId",order.getId());
        return true;
    }

    private Long calculateAmount(Long itemId, Integer count) {
        return 100L*count;
    }

    @Override
    public boolean compensatePurchaseItem(String businessKey, Long userId, Long itemId, Integer count, Map<String, Object> params) {
        log.info("开始尝试回滚购买商品 businessKey={}",businessKey);
        Long orderId = (Long) params.getOrDefault("orderId",null);
        if (Objects.isNull(orderId)) {
            return true;
        }
        Account account = accountRepository.findAccountByUserId(userId);
        Long amount = calculateAmount(itemId, count);

        account.setMoney(account.getMoney()+amount);
        accountRepository.save(account);


        orderRepository.deleteById(orderId);

        return true;
    }

}
