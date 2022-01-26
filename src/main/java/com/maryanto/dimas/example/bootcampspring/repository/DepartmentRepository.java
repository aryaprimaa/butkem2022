package com.maryanto.dimas.example.bootcampspring.repository;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class DepartmentRepository {

    @Autowired
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    @Transactional(readOnly = false)
    public Department updateDepartemen(Department dept) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "UPDATE department SET name=:name , description=:description WHERE ID=:id ";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", dept.getNama());
        map.addValue("description", dept.getDescription());
        map.addValue("id", dept.getId());
        this.namedJdbcTemplate.update(sql, map, keyHolder);
        return dept;
    }

    public List<Department> list() {
        return this.namedJdbcTemplate.query(
                "select * from department ORDER BY department_id ",
                new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Department(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
                    }
                });
    }

    public Department findById(Integer id) throws EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject("select * from department where department_id = :id",
                map, new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Department(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
                    }
                });
    }

    public Department findByIdDuplicate(Integer id) throws IncorrectResultSizeDataAccessException, EmptyResultDataAccessException {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject("select * from department",
                map, new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Department(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
                    }
                });
    }

    public Department findByIdNoThrowing(Integer id) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("id", id);
        return this.namedJdbcTemplate.queryForObject("select * from department where department_id = :id",
                map, new RowMapper<Department>() {
                    @Override
                    public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
                        return new Department(
                                rs.getInt(1),
                                rs.getString(2),
                                rs.getString(3));
                    }
                });
    }

    @Transactional
    public Integer insert(Department value) throws SQLException{
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO department (name,description) VALUES (:name,:description)";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name",value.getNama());
        map.addValue("description",value.getDescription());
        this.namedJdbcTemplate.update(sql,map,keyHolder);
        return (Integer) keyHolder.getKeys().get("department_id");
    }


    @Transactional
    public void updateById(Department value) {
        String query = "update department set name = :name, desc= :desc " + "where department_id = :id";
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("name", value.getNama());
        map.addValue("id", value.getId());
        this.namedJdbcTemplate.update(query, map);
    }
    @Transactional
    public void deleteById(Department value) {
        MapSqlParameterSource map = new MapSqlParameterSource();
        map.addValue("nama", value.getNama());
        map.addValue("id", value.getId());
        map.addValue("description", value.getDescription());

        String query = "DELETE FROM category where id = :id";

        this.namedJdbcTemplate.update(query, map);
    }
}
