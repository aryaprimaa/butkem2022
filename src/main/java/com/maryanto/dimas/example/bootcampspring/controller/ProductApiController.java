package com.maryanto.dimas.example.bootcampspring.controller;


import com.maryanto.dimas.example.bootcampspring.entity.DataTableRequest;
import com.maryanto.dimas.example.bootcampspring.entity.DataTableResponse;
import com.maryanto.dimas.example.bootcampspring.entity.Product;
import com.maryanto.dimas.example.bootcampspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {
    @Autowired
    private ProductRepository productRepository;


    @GetMapping("/check/{id}")
    public ResponseEntity<Product> getById(@PathVariable Integer id) throws EmptyResultDataAccessException {
        Product product = productRepository.findById(id);
        return ResponseEntity.ok(product);
    }

    @PostMapping("/listproduk")
    public ResponseEntity<DataTableResponse<Product>>
        listProduct(DataTableRequest req) {
        DataTableResponse<Product> drepo = new DataTableResponse<>();
        drepo.setData(productRepository.list(req));
        drepo.setDraw(req.getDraw());
        Long banyak = productRepository.countProduct(req);
        drepo.setRecordsFiltered(banyak);
        drepo.setRecordsTotal(banyak);
        return ResponseEntity.ok(drepo);

    }
}
