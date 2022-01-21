package com.maryanto.dimas.example.bootcampspring.repository;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class CategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public List<Category> list() {
        return this.namedJdbcTemplate.query("select * from category", new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Category(rs.getInt(1), rs.getString("nama"), rs.getString("description"), rs.getInt("department_id"));
            }
        });
    }

    public Category findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject("select * from category where category_id = :id", map, new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Category(rs.getInt("category_id"), rs.getString("nama"), rs.getString("description"), rs.getInt("department_id"));
            }
        });
    }

    @Transactional
    public Category insert(Category value) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", value.getNama());
        map.addValue("description", value.getDescription());
        map.addValue("department_id", value.getDepartmentt());


        String query = "INSERT INTO category (name, description, department_id) values (:nama, :description,:department_id)";

        this.namedJdbcTemplate.update(query, map);
        return value;
    }

    @Transactional
    public void updateById(Category value) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", value.getNama());
        map.addValue("id", value.getId());
        map.addValue("description", value.getDescription());

        String query = "update category set name = :name , description = :desc where category_id = :id";

        this.namedJdbcTemplate.update(query, map);
    }

    @Transactional
    public void deleteById(Category value) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", value.getNama());
        map.addValue("id", value.getId());
        map.addValue("description", value.getDescription());
        map.addValue("department_id", value.getDepartmentt());

        String query = "DELETE FROM category where category_id = :id";

        this.namedJdbcTemplate.update(query, map);
    }

    @Transactional
    public List<Category> listDepartment() {
        return this.namedJdbcTemplate.query(
                "SELECT category.category_id as category_id, " + "category.department_id as department_id, category.name as category_name," + " category.description as category_description," + " department.name as department_name, " + "department.description as department_description " + "FROM public.category INNER JOIN department ON category.department_id=department.department_id;",
                new RowMapper<Category>() {
            @Override
            public Category mapRow(ResultSet rs, int rowNum) throws SQLException {
                Department t= new Department(rs.getInt(2), rs.getString(5), rs.getString(6));
                return new Category(
                        rs.getInt("category_id"),
                        rs.getString("nama"),
                        rs.getString("description"),
                        rs.getInt("department"));
            }
        });
    }
}