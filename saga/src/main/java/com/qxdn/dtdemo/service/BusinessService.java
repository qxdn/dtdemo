package com.qxdn.dtdemo.service;

import io.seata.saga.engine.StateMachineEngine;
import io.seata.saga.statelang.domain.StateMachineInstance;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class BusinessService {


    @Autowired
    private StateMachineEngine stateMachineEngine;

    public void purchase(Long userId, Long itemId, Integer count) {
        Map<String, Object> startParams = new HashMap<>(3);
        String businessKey = String.valueOf(System.currentTimeMillis());
        startParams.put("businessKey", businessKey);
        startParams.put("userId", userId);
        startParams.put("itemId", itemId);
        startParams.put("count", count);
        StateMachineInstance inst = stateMachineEngine.startWithBusinessKey("StateMachine-purchase", null, businessKey, startParams);
        log.info("SAGA状态机结果:{}", inst.getStatus().getStatusString());
    }
}
