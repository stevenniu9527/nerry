package com.steven.cloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "order")
public interface OrderFeign {

    @GetMapping("/create")
    void create(@RequestParam("userId") String userId,
                @RequestParam("productId") String productId,
                @RequestParam("count") Integer count, @RequestParam("money") int money);
}