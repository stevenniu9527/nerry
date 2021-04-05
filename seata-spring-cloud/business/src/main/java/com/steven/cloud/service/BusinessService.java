package com.steven.cloud.service;

import java.util.Map;

public interface BusinessService {
    String processTcc(Map<String, String> params);

    String processAt(String userId, int orderMoney, String commodityCode, int count) throws InterruptedException;
}
