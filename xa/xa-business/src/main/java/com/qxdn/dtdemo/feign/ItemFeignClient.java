package com.qxdn.dtdemo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "item-service",url = "127.0.0.1:8081")
public interface ItemFeignClient {

    @PostMapping("/purchaseItem")
    void purchaseItem(@RequestParam Long itemId,@RequestParam Integer count);
}
