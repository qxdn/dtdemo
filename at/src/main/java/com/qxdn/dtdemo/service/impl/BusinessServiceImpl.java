package com.qxdn.dtdemo.service.impl;

import com.qxdn.dtdemo.service.BusinessService;
import com.qxdn.dtdemo.service.ItemService;
import com.qxdn.dtdemo.service.OrderService;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderService orderService;

    @Override
    @GlobalTransactional
    public void purchaseItem(Long userId, Long itemId, Integer count) {
        log.info("start business service xid:{}", RootContext.getXID());
        itemService.purchaseItem(itemId, count);
        orderService.createOrder(userId, itemId, count);
    }
}
