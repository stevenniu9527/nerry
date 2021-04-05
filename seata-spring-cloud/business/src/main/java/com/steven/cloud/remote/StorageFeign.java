package com.steven.cloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "storage")
public interface StorageFeign {

    @GetMapping("/decrease")
    void deduct(@RequestParam("productId") String commodityCode,
                @RequestParam("count") Integer count);

}