package com.qxdn.dtdemo.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "com.qxdn.dtdemo.feign")
public class FeignConfig {
}
