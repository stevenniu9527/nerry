package com.steven.cloud.service;

import java.util.List;
import java.util.Map;

public interface StorageService {
    boolean decrease(String productId, Integer count);

    List<Map<String, Object>> queryData();
}

