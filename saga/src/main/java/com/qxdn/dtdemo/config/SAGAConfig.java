package com.qxdn.dtdemo.config;

import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.engine.config.DbStateMachineConfig;
import io.seata.saga.engine.impl.ProcessCtrlStateMachineEngine;
import io.seata.saga.rm.StateMachineEngineHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SAGAConfig {

    @Autowired
    private DataSource dataSource;

    @Bean
    public  DbStateMachineConfig dbStateMachineConfig(){
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 20, 60, java.util.concurrent.TimeUnit.SECONDS, new java.util.concurrent.LinkedBlockingQueue<>(1000));
        DbStateMachineConfig config = new DbStateMachineConfig();
        config.setDataSource(dataSource);
        config.setResources(new String[]{"classpath*:saga/*.json"});
        config.setEnableAsync(true);
        config.setThreadPoolExecutor(executor);
        return config;
    }

    @Bean
    public StateMachineEngine stateMachineEngine() {
        ProcessCtrlStateMachineEngine engine = new ProcessCtrlStateMachineEngine();
        engine.setStateMachineConfig(dbStateMachineConfig());
        return engine;
    }

    @Bean
    public StateMachineEngineHolder stateMachineEngineHolder(){
        StateMachineEngineHolder engineHolder = new StateMachineEngineHolder();
        engineHolder.setStateMachineEngine(stateMachineEngine());
        return engineHolder;
    }
}
