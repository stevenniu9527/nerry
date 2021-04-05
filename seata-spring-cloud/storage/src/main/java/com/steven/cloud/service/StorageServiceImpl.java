package com.steven.cloud.service;

import com.steven.cloud.dao.StorageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageDao dao;

    @Override
    public boolean decrease(String productId, Integer count) {
        return dao.decrease(productId, count);
    }

    @Override
    public List<Map<String, Object>> queryData() {
        return dao.queryAll();
    }
}
