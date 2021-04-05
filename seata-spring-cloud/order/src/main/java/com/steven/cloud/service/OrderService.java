package com.steven.cloud.service;

import com.steven.cloud.bean.Order;

import java.util.List;
import java.util.Map;

public interface OrderService {
    boolean createOrder(Order order);

    List<Map<String, Object>> queryData();
}
