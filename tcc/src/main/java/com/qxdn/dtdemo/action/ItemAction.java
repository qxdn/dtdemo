package com.qxdn.dtdemo.action;

import io.seata.rm.tcc.api.BusinessActionContext;

public interface ItemAction {


    boolean decreaseStock(BusinessActionContext actionContext, Long itemId, Integer count);

    boolean commit(BusinessActionContext actionContext);

    boolean cancel(BusinessActionContext actionContext);
    
}
