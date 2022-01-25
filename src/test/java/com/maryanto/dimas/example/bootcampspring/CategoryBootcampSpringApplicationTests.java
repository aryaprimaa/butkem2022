package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Category;
import com.maryanto.dimas.example.bootcampspring.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class CategoryBootcampSpringApplicationTests {

    @Autowired
    private CategoryRepository catRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testInsertData() {
        Category category = new Category(null, "ARYKA", "AYAK", 1);
        category = this.catRepo.insert(category);
        System.out.println(category.getId());
    }

    @Test
    void testUpdateData() {
        Category catt = new Category(18, "Bibi", "biba", 4);
        this.catRepo.updateById(catt);
        System.out.println(catt.toString());


        System.out.println(catt);
    }
    @Test
    void testDeleteData() {
        Category catt = new Category(29, null, null, null);
        this.catRepo.deleteById(catt);
        System.out.println(catt.toString());
        System.out.println(catt);

    }

    @Test
    void testDataCategory() {
        List<Category> list = this.catRepo.list();
        list.forEach(data -> {
            System.out.println(data.getNama());
        });
        List<String> listNama = list.stream()
                .map(Category::getNama).collect(Collectors.toList());

        try {
            Category cat = this.catRepo.findById(6);
            System.out.println(cat.getNama());
        } catch (EmptyResultDataAccessException erda) {
            System.out.println("datanya kosong!");
        } catch (IncorrectResultSizeDataAccessException irsdae) {
            System.out.println("datanya lebih dari 1");
        }
    }

}
