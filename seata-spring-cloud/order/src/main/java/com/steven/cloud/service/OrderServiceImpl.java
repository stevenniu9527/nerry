package com.steven.cloud.service;

import com.steven.cloud.bean.Order;
import com.steven.cloud.dao.OrderDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao dao;

    @Override
    public boolean createOrder(Order order) {
        return dao.createOrder(order);
    }

    @Override
    public List<Map<String, Object>> queryData() {
        return dao.queryAll();
    }
}
