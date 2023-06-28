package com.temychp.fitccalc.dao;

import com.temychp.fitccalc.models.product.Product;
import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@AllArgsConstructor
@Component
public class ProductDAO {
    private final JdbcTemplate jdbcTemplate;

    public List<Product> showProductsCreatedByPersonOrderByName(Long id) {
        return jdbcTemplate.query("SELECT * FROM Product WHERE created_person_id=? ORDER BY name",
                new BeanPropertyRowMapper<>(Product.class), id);
    }

    public List<Product> showProductsOrderByMinCalories(int count) {
        return jdbcTemplate.query("SELECT * FROM Product ORDER BY calories LIMIT ?",
                new BeanPropertyRowMapper<>(Product.class), count);
    }


}