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

    @PostMapping("/savedept")
    public ResponseEntity<?> saveDeptJson(@Valid @RequestBody Department dept , BindingResult result) {
        Map<String, Object> hasil1 = new HashMap<>();
        if (result.hasErrors()) {
            DepartmentRepository.updateDepartemen(dept);
            hasil1.put("id", -1);
            hasil1.put("status", result.getAllErrors());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(hasil1);
        } else {
            dept = this.repo.insert(dept);
            if (dept.getId() == null) {
                return ResponseEntity.internalServerError().body("Gak ketemu IDnya gan");
            } else {
                Map<String, Object> hasil2 = new HashMap<>();
                DepartmentRepository.updateDepartemen(dept);
                hasil2.put("id", 0);
                hasil2.put("status", "Update Berhasil");
                return ResponseEntity.ok(dept);
            }
        }
    }

    @PostMapping("/updatedept")
    public ResponseEntity<Map<String, Object>> updateById(@RequestBody Department dept) {
        Map<String, Object> hasil = new HashMap<>();
        DepartmentRepository.updateDepartemen(dept);
        hasil.put("id", 0);
        hasil.put("status", "Update Berhasil");
        return ResponseEntity.ok(hasil);
    }

    @DeleteMapping("/delete/{reqid}")
    public ResponseEntity<Map<String, Object>> deleteById(@RequestBody Department dept) {
        Map<String, Object> hasil = new HashMap<>();
        DepartmentRepository.updateDepartemen(dept);
        hasil.put("id", 0);
        hasil.put("status", "Update Berhasil");
        return ResponseEntity.ok(hasil);
    }
}