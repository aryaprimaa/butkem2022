package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import jdk.javadoc.internal.tool.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.xml.transform.Result;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/department")
public class DepartmentApiController {

    @Autowired
    private DepartmentRepository repo;

    @GetMapping("/findById/{depId}")
    public ResponseEntity<Department> findById(@PathVariable("depId") Integer id) {
        try {
            Department dep = repo.findById(id);
            return ResponseEntity.ok(dep);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("/listdept")
    public List<Department> list() {
        return this.repo.list();
    }

    // Save Perubahan
    @PostMapping("/savedept")
    public ResponseEntity<?> saveDeptJson(@Valid @RequestBody Department dept , BindingResult result) {
        Map<String, Object> hasil = new HashMap<>();
        try {
            if (result.hasErrors()) {
                hasil.put("id", null);
                hasil.put("status", result.getAllErrors());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
            } else {
                hasil.put("id",repo.insert(dept));
                hasil.put("status", "Success added new Department");
                return ResponseEntity.ok(hasil);
            }
        } catch (SQLException e) {
            hasil.put("id", null);
            hasil.put("status", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil);
        }

    }

    @PutMapping("/updatedept")
    public ResponseEntity<?> updateById(@RequestBody Department dept) {
        Map<String, Object> hasil = new HashMap<>();
        this.repo.updateDepartemen(dept);
        hasil.put("id", null);
        hasil.put("status", "Update Berhasil");
        return ResponseEntity.ok(hasil);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Map<String, Object>> deleteById(@RequestBody Department dept) {
        Map<String, Object> hasil = new HashMap<>();
        this.repo.deleteById(dept);
        hasil.put("id", null);
        hasil.put("status", "Delete Berhasil");
        return ResponseEntity.ok(hasil);
    }
}