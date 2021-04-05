package com.steven.cloud.controller;

import com.steven.cloud.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("seata")
public class BusinessController {

    @Autowired
    private BusinessService service;

    @GetMapping("/at")
    public String create(@RequestParam String name) throws InterruptedException {
        int money = (new Random().nextInt(10) + 1) * 300;
        return service.processAt(name, money, "1", 1);
    }

    @GetMapping("/tcc")
    public String insertTcc(@RequestParam String name) {
        Map<String, String> params = new HashMap<>(1);
        params.put("name", name);
        return service.processTcc(params);
    }
}
