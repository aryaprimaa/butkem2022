package com.maryanto.dimas.example.bootcampspring;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@SpringBootTest
class BootcampSpringApplicationTests {

    @Autowired
    private DepartmentRepository depRepo;

    @Test
    void contextLoads() {
    }

    @Test
    void testUpdateData() {
        Department depAwal = this.depRepo.findById(1);
        Department department = new Department(1, UUID.randomUUID().toString(), null);
        this.depRepo.updateDepartemen(department);

        Department newDepartment = this.depRepo.findById(department.getId());
        Assertions.assertEquals(department.getNama(), newDepartment.getNama());
        Assertions.assertNotEquals(depAwal.getNama(), newDepartment.getNama());
    }

    @Test
    void testDataDepartment() {
        List<Department> list = this.depRepo.list();
        list.forEach(data -> {
            System.out.println(data.getNama());
        });
        List<String> listNama = list.stream()
                .map(Department::getNama).collect(Collectors.toList());

        try {
            Department dep = this.depRepo.findByIdDuplicate(6);
            System.out.println(dep.getNama());
        } catch (EmptyResultDataAccessException erda) {
            System.out.println("datanya kosong!");
        } catch (IncorrectResultSizeDataAccessException irsdae) {
            System.out.println("datanya lebih dari 1");
        }
    }
        @Test
        void testDeleteData() {
            Department dept = new Department(1, null, null);
            this.depRepo.deleteById(dept);
            System.out.println(dept.toString());
            System.out.println(dept);

        }
    }