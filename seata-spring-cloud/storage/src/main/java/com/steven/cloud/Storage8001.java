package com.steven.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class Storage8001 {
    public static void main(String[] args) {
        SpringApplication.run(Storage8001.class);
    }
}
