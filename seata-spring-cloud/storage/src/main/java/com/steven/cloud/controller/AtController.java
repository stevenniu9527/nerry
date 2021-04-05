package com.steven.cloud.controller;

import com.google.gson.Gson;
import com.steven.cloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AtController {

    @Autowired
    private StorageService storageService;

    @RequestMapping("decrease")
    public String decrease(@RequestParam("productId") String productId, @RequestParam("count") Integer count) {
        boolean result = storageService.decrease(productId, count);
        return "Decrease storage :" + result;
    }

    @GetMapping("/dbdata")
    public String test() {
        return new Gson().toJson(storageService.queryData());
    }
}
