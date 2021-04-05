package com.steven.cloud.service;

import io.seata.rm.tcc.api.BusinessActionContext;
import io.seata.rm.tcc.api.BusinessActionContextParameter;
import io.seata.rm.tcc.api.LocalTCC;
import io.seata.rm.tcc.api.TwoPhaseBusinessAction;

import java.util.Map;

@LocalTCC
public interface TccService {
    @TwoPhaseBusinessAction(name = "upload", commitMethod = "commitTcc", rollbackMethod = "cancel")
    String upload(@BusinessActionContextParameter(paramName = "params") Map<String, String> params);

    boolean commitTcc(BusinessActionContext context);

    boolean cancel(BusinessActionContext context);
}
