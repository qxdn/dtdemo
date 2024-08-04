package com.qxdn.dtdemo.action;

import io.seata.rm.tcc.api.BusinessActionContext;

public interface OrderAction {


    boolean purchaseItem(BusinessActionContext actionContext,Long userId, Long itemId, Integer count);

    boolean commit(BusinessActionContext actionContext);

    boolean cancel(BusinessActionContext actionContext);
}
