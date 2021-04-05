package com.steven.cloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "upload-server")
public interface UploadFeign {

    @PostMapping(value = "/upload")
    String upload(Map<String, String> params);
}
