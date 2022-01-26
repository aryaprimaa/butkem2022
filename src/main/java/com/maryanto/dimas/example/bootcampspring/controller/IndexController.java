package com.maryanto.dimas.example.bootcampspring.controller;

import com.maryanto.dimas.example.bootcampspring.entity.Department;
import com.maryanto.dimas.example.bootcampspring.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/web")
public class IndexController {

    @Autowired
    private DepartmentRepository repo;

    @GetMapping("/index")
    public String showIndex(Model model) {
        List<Department> list = this.repo.list();
        model.addAttribute("data", new Department());
        model.addAttribute("listDepartment", list);
        return "index";
    }

    @PostMapping("/test")
    public String processRequest(
            @RequestParam(name = "nama") String nama,
            @RequestParam("description") String description) {
        System.out.println("nama: " + nama + ", description: " + description);
        return "redirect:/web/index";
    }

    @PostMapping("/test2")
    public String processRequest2 (
            @ModelAttribute Department dept, RedirectAttributes rattrs) throws SQLException {
        this.repo.insert(dept);
        rattrs.addFlashAttribute("successInfo", true);
        return "redirect:/web/index";
    }
}
