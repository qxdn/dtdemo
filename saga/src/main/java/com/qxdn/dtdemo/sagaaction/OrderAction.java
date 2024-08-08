package com.qxdn.dtdemo.sagaaction;


import java.util.Map;

public interface OrderAction {


    boolean purchaseItem(String businessKey, Long userId, Long itemId, Integer count, Map<String,Object> params);

    boolean compensatePurchaseItem(String businessKey, Long userId, Long itemId, Integer count, Map<String,Object> params);
}
