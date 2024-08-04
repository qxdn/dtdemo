package com.qxdn.dtdemo.dal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.qxdn.dtdemo.dal.repository")
public class JPAConfig {
}
