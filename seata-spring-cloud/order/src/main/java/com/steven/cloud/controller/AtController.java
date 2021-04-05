package com.steven.cloud.controller;

import com.google.gson.Gson;
import com.steven.cloud.bean.Order;
import com.steven.cloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtController {

    @Autowired
    private OrderService orderService;

    @GetMapping("create")
    public String create(Order order) {
        boolean result = orderService.createOrder(order);
        return "Create order :" + result;
    }

    @GetMapping("/dbdata")
    public String test() {
        return new Gson().toJson(orderService.queryData());
    }
}
