package com.maryanto.dimas.example.bootcampspring.entity;

public class Category {

    public Category() {
    }

    public Category(Integer id, String nama, String description, Integer departmentt) {
        this.id = id;
        this.nama = nama;
        this.description = description;
        this.departmentt = departmentt;
    }

    public Integer id;
    public String nama;
    public Integer departmentt;
    public String description;
    public Department department;

    public Category(Integer id, String nama, Integer departmentt, String description, Department department) {
        this.id = id;
        this.nama = nama;
        this.description = description;
        this.departmentt = departmentt;
        this.department = department;
    }

    public void setId(Integer id) {this.id = id; }
    public void setNama(String nama) { this.nama = nama; }
    public void setDescription(String description) {
        this.description=description;
    }
    public void setDepartmentt(Integer departmentt) {this.departmentt=departmentt; }
    public Integer getId(){
        return id;
    }
    public String getNama(){
        return nama;
    }
    public String getDescription(){
        return description;
    }
    public Integer getDepartmentt() { return departmentt; }
}