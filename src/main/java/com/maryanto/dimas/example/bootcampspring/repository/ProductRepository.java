package com.maryanto.dimas.example.bootcampspring.repository;

import com.maryanto.dimas.example.bootcampspring.entity.DataTableRequest;
import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Transactional
public class ProductRepository {

    @Autowired
    private DataSource dataSource;
    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Product> list(DataTableRequest req) {
        Map<String,Object> paramMap = new HashMap<>();
        paramMap.put("limit", req.getLength());
        paramMap.put("offset", req.getStart());
        String sql = "SELECT product_id, name, description, price FROM public.product "
                + "WHERE name ilike '$'||:name||" +
                "ORDER BY" + (req.getSortCol() + 1) + " " + req.getSortDir() +
                "limit:limit offset:offset";
        return namedJdbcTemplate.query(sql,paramMap,new RowMapper<Product>()
        {
                @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Product product = new Product();
                        product.setId(rs.getInt("product_id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        return product;
                    }
                });
    }
    public Product findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject
                ("SELECT product_id, name, description, price FROM public.product where product_id = :id",
                map, new RowMapper<Product>() {
                    @Override
                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Product product = new Product();
                        product.setId(rs.getInt("product_id"));
                        product.setName(rs.getString("name"));
                        product.setDescription(rs.getString("description"));
                        product.setPrice(rs.getDouble("price"));
                        return product;
                    }
                });
    }
    public Long countProduct(DataTableRequest req){
        Map<String,Object> paramMap = new HashMap<>();
        Map<String,Object> extraParam = req.getExtraParam();
        String namaParam = (String) extraParam.get("nama");
        paramMap.put("name", namaParam);
        String sql = "SELECT COUNT(distinct(a.product_id) as banyak from public product";
                return namedJdbcTemplate.queryForObject(sql,paramMap,Long.class);
    }
}
