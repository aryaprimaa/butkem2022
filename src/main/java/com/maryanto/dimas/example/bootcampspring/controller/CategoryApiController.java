package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryApiController {
    @Autowired
    CategoryRepository catrep;

    @GetMapping("/list")
    public List<Category> list() {
        return this.catrep.list();
    }

    @GetMapping("findById/{reqid}")
    public ResponseEntity<Category> findById(@PathVariable("reqid") Integer id){
        try {
            Category cat = catrep.findById(id);
            return ResponseEntity.ok(cat);
        } catch (EmptyResultDataAccessException ertdae) {
            return ResponseEntity.noContent().build();
        }
    }
    @PostMapping
    public ResponseEntity<?> save(@RequestBody Category cat) {
        cat = this.catrep.insert(cat);
        if (cat.getId() == null) {
            return ResponseEntity.internalServerError().body("Gak tau errornya apa");
        } else {
            return ResponseEntity.ok(cat);
        }
    }
    @PutMapping("/update")
    public ResponseEntity<?> update(@RequestBody Category cat) throws SQLException {
        this.catrep.updateById(cat);
        return ResponseEntity.ok(cat);
    }

    @DeleteMapping("/delete/{reqid}")
    public ResponseEntity<?> delete(@PathVariable Category cat) throws SQLException{
        this.catrep.deleteById(cat);
        return ResponseEntity.ok("Berhasil di hapus!!");
    }
    @GetMapping("/department")
    public List<Category> listDepart(){
        return this.catrep.listDepartment();
    }
}

