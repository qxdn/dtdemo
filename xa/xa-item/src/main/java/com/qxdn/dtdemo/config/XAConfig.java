package com.qxdn.dtdemo.config;

import io.seata.spring.annotation.datasource.EnableAutoDataSourceProxy;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoDataSourceProxy(dataSourceProxyMode = "XA")
public class XAConfig {
}
