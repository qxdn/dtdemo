package com.qxdn.dtdemo.service;

import com.qxdn.dtdemo.action.ItemAction;
import com.qxdn.dtdemo.action.OrderAction;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BusinessService {

    @Autowired
    private ItemAction itemAction;

    @Autowired
    private OrderAction orderAction;

    @GlobalTransactional
    public void purchase(Long userId, Long itemId, Integer count) {
        itemAction.decreaseStock(null, itemId, count);
        orderAction.purchaseItem(null, userId, itemId, count);
    }
}
