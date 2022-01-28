package com.maryanto.dimas.example.bootcampspring.entity;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotEmpty;

public class Product{
    private int id;
    @NotEmpty
    @Length(min=4)
    private String nama;
    @NotEmpty
    @Length(min=5)
    private String description;
    private Double price;

    public Product(){
    }
    public Integer getId() {return id;}
    public void setId(Integer id) {this.id = id;}

    public String getName() {return nama;}
    public void setName(String nama) {this.nama = nama;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public Double getPrice() {return price;}
    public void setPrice(Double price) {this.price = price;}

    public Product(Integer id, String nama, String description, double price){
        this.nama = nama;
        this.id = id;
        this.description=description;
        this.price=price;
    }
}
