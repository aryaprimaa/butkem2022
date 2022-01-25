package com.maryanto.dimas.example.bootcampspring.entity;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class Department {
    private int id;

    @NotEmpty
    @Length(min=4)
    private String nama;

    @NotEmpty
    @Length(min=5)
    private String description;

    public Department(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Department(Integer id, String nama, String description){
        this.nama = nama;
        this.id = id;
        this.description=description;
    }
}