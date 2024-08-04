package com.qxdn.dtdemo.controller;

import com.qxdn.dtdemo.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ATController {

    @Autowired
    private BusinessService businessService;

    @PostMapping("/purchaseItem")
    public void purchaseItem(Long userId, Long itemId, Integer count) {
        businessService.purchaseItem(userId, itemId, count);
    }
}
