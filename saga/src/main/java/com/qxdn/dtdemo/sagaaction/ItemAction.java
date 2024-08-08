package com.qxdn.dtdemo.sagaaction;


import java.util.Map;

public interface ItemAction {


    boolean decreaseStock(String businessKey, Long itemId, Integer count, Map<String,Object> params);


    boolean compensateDecreaseStock(String businessKey, Long itemId, Integer count, Map<String,Object> params);
}
