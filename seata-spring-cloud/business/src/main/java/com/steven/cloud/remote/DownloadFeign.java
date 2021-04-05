package com.steven.cloud.remote;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@FeignClient(value = "download-server")
public interface DownloadFeign {

    @PostMapping(value = "/download")
    String download(Map<String, String> params);
}