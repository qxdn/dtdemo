package com.qxdn.dtdemo.action.impl;

import com.qxdn.dtdemo.action.ItemAction;
import com.qxdn.dtdemo.dal.model.Item;
import com.qxdn.dtdemo.dal.repository.ItemRepository;
import io.seata.rm.tcc.api.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@LocalTCC
@Slf4j
@Service
public class ItemActionImpl implements ItemAction {

    @Autowired
    private ItemRepository itemRepository;

    @TwoPhaseBusinessAction(name = "decreaseStock", commitMethod = "commit", rollbackMethod = "cancel")
    @Override
    public boolean decreaseStock(BusinessActionContext actionContext, @BusinessActionContextParameter("itemId") Long itemId,
                                 @BusinessActionContextParameter("count") Integer count) {
        log.info("开始尝试减少库存 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Item item = itemRepository.findItemById(itemId);
        if (item.getCount() < count) {
            throw new RuntimeException("库存不足");
        }
        item.setCount(item.getCount() - count);
        item.setTransactionalCount(item.getTransactionalCount() + count);
        itemRepository.save(item);
        // 恢复事务金额
        BusinessActionContextUtil.addContext("recoverCount",count);
        return true;
    }

    @Override
    public boolean commit(BusinessActionContext actionContext) {
        log.info("开始提交减少库存 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Long itemId = ((Number) actionContext.getActionContext("itemId")).longValue();
        Integer count = (Integer) actionContext.getActionContext("count");
        count = count==null?0:count;//临时解决方案
        Item item = itemRepository.findItemById(itemId);
        item.setTransactionalCount(item.getTransactionalCount()-count);
        itemRepository.save(item);
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext actionContext) {
        log.info("开始回滚减少库存 事务xid={} 分支事务bid={}",actionContext.getXid(),actionContext.getBranchId());
        Long itemId = ((Number) actionContext.getActionContext("itemId")).longValue();
        Integer count = (Integer) actionContext.getActionContext("recoverCount");
        count = count==null?0:count;//临时解决方案
        Item item = itemRepository.findItemById(itemId);
        // 回滚 恢复事务金额
        item.setTransactionalCount(item.getTransactionalCount()-count);
        item.setCount(item.getCount() + count);
        itemRepository.save(item);
        return true;
    }
}
