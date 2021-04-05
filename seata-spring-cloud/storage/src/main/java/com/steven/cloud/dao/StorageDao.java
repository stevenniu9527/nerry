package com.steven.cloud.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StorageDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean decrease(String productId, Integer count) {
        String sql = "UPDATE product_storage SET used = used + " + count + ",residue = residue - " + count + " WHERE product_id = " + productId;
        return jdbcTemplate.update(sql) > 0;
    }

    public List<Map<String, Object>> queryAll() {
        String sql = "SELECT * FROM product_storage";
        return jdbcTemplate.queryForList(sql);
    }
}
