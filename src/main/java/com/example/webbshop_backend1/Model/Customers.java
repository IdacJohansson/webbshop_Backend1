package com.example.webbshop_backend1.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Customers {

    @Id
    @GeneratedValue
    private Long id;
    private String customerName;
    private String ssn;

    public Customers(String customerName, String ssn){
        this.customerName = customerName;
        this.ssn = ssn;

    }

}
