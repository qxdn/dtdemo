package com.qxdn.dtdemo.controller;

import com.qxdn.dtdemo.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class XAItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("/purchaseItem")
    public void purchaseItem(Long itemId, Integer count) {
        itemService.purchaseItem(itemId, count);
    }
}
