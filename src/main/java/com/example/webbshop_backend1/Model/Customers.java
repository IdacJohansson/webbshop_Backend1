package com.example.webbshop_backend1.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Customers {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String ssn;

    public Customers(String name, String ssn){
        this.name = name;
        this.ssn = ssn;

    }



}
