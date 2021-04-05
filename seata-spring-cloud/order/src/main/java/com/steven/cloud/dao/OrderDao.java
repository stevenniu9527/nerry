package com.steven.cloud.dao;

import com.steven.cloud.bean.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class OrderDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public boolean createOrder(Order order) {
        String sql = "INSERT INTO product_order (user_id,product_id,count,money,status) VALUES('" + order.getUserId() + "', " + order.getProductId() + "," + order.getCount() + ", " + order.getMoney() + ",0);";
        return jdbcTemplate.update(sql) > 0;
    }

    public List<Map<String, Object>> queryAll() {
        String sql = "SELECT * FROM product_order";
        return jdbcTemplate.queryForList(sql);
    }
}
