package com.qxdn.dtdemo.service;

public interface OrderService {

    void createOrder(Long userId, Long itemId, Integer count);
}
