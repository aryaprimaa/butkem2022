//package com.maryanto.dimas.example.bootcampspring.repository;
//
//import com.maryanto.dimas.example.bootcampspring.entity.Department;
//import com.maryanto.dimas.example.bootcampspring.entity.Product;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
//import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.sql.DataSource;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.util.List;
//
//@Repository
//@Transactional
//public class ProductRepository {
//
//    @Autowired
//    private DataSource dataSource;
//    @Autowired
//    private NamedParameterJdbcTemplate namedJdbcTemplate;
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public List<Product> list() {
//        return this.namedJdbcTemplate.query(
//                "select * from product ORDER BY department_id ",
//                new RowMapper<Department>() {
//                    @Override
//                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new Product(
//                                rs.getInt(1),
//                                rs.getString(2),
//                                rs.getString(3));
//                    }
//                });
//    }
//    public Product findById(Integer id) throws EmptyResultDataAccessException {
//        MapSqlParameterSource map = new MapSqlParameterSource();
//        map.addValue("id", id);
//        return this.namedJdbcTemplate.queryForObject("select * from product where product = :id",
//                map, new RowMapper<Department>() {
//                    @Override
//                    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
//                        return new Product(
//                                rs.getInt(1),
//                                rs.getString(2),
//                                rs.getString(3));
//                    }
//                });
//    }
//}
