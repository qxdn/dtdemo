package com.qxdn.dtdemo.service.impl;

import com.qxdn.dtdemo.dal.model.Order;
import com.qxdn.dtdemo.dal.repository.OrderRepository;
import com.qxdn.dtdemo.service.AccountService;
import com.qxdn.dtdemo.service.OrderService;
import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private AccountService accountService;

    @Override
    public void createOrder(Long userId, Long itemId, Integer count) {
        log.info("start order service xid:{}", RootContext.getXID());
        Integer amount = calculateAmount(itemId, count);

        accountService.debit(userId, amount);

        Order order = new Order();
        order.setUserId(userId);
        order.setItemId(itemId);
        order.setCount(count);
        order.setAmount(amount);
        // save order
        orderRepository.save(order);
    }

    private Integer calculateAmount(Long itemId, Integer count) {
        // calculate amount
        return count * 100;
    }
}
