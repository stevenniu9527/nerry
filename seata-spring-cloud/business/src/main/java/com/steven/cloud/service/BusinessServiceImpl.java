package com.steven.cloud.service;

import com.steven.cloud.remote.DownloadFeign;
import com.steven.cloud.remote.OrderFeign;
import com.steven.cloud.remote.StorageFeign;
import com.steven.cloud.remote.UploadFeign;
import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@Service
public class BusinessServiceImpl implements BusinessService {
    @Autowired
    private UploadFeign uploadFeign;
    @Autowired
    private DownloadFeign downloadFeign;
    @Autowired
    private StorageFeign storageFeign;
    @Autowired
    private OrderFeign orderFeign;

    private AtomicInteger visitCount = new AtomicInteger(0);

    @Override
    @GlobalTransactional
    public String processTcc(Map<String, String> params) {
        String xid = RootContext.getXID();
        System.out.println(("---》》》》xid：" + xid));
        uploadFeign.upload(params);
        downloadFeign.download(params);
        return xid;
    }


    @Override
    @GlobalTransactional(rollbackFor = Exception.class)
    public String processAt(String userId, int orderMoney, String commodityCode, int count) throws InterruptedException {
        String xid = RootContext.getXID();
        System.out.println("---》》》》xid：" + xid);
        System.out.println(("------->创建订单开始"));
        orderFeign.create(userId, commodityCode, count, orderMoney);
        System.out.println(("------->创建订单结束"));

        System.out.println(("------->扣减库存开始"));
        storageFeign.deduct(commodityCode, count);
        System.out.println(("------->扣减库存结束"));

        if (visitCount.incrementAndGet() % 3 == 0) {
            TimeUnit.SECONDS.sleep(30);
            throw new RuntimeException("process failed");
        }
        return xid;
    }
}
