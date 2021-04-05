package com.steven.cloud.service.impl;

import com.steven.cloud.bean.Result;
import com.steven.cloud.service.TccService;
import io.seata.core.context.RootContext;
import io.seata.rm.tcc.api.BusinessActionContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Map;

@Slf4j
@Service
public class TccServiceImpl implements TccService {
    @Value("${spring.application.name}")
    private String appName;

    @PostConstruct
    private void initAppName() {
        Result.getResult().setAppName(appName);
    }

    @Override
    public String upload(Map<String, String> params) {
        String xid = RootContext.getXID();
        System.out.println(("---》》》》xid： " + xid));
        return "success";
    }

    @Override
    public boolean commitTcc(BusinessActionContext context) {
        String xbid = context.getXid();
        System.out.println(("---》》》》xid： " + xbid + "提交成功"));
        Result.getResult().setActionResult(context.getXid(), +context.getBranchId(), "Commit", context.getActionContext("params"));
        return true;
    }

    @Override
    public boolean cancel(BusinessActionContext context) {
        System.out.println(("---》》》》xid： " + context.getXid() + "回滚成功"));
        Result.getResult().setActionResult(context.getXid(), context.getBranchId(), "Rollback", context.getActionContext("params"));
        return true;
    }
}
