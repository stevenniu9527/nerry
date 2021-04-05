package com.steven.cloud.controller;

import com.google.gson.Gson;
import com.steven.cloud.bean.Result;
import com.steven.cloud.service.TccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TccController {

    @Autowired
    private TccService service;

    @PostMapping("/upload")
    public String insertTcc(@RequestBody Map<String, String> params) {
        return service.upload(params);
    }

    @GetMapping(value = "/result", produces = MediaType.APPLICATION_JSON_VALUE)
    public String result() {
        return new Gson().toJson(Result.getResult());
    }
}
