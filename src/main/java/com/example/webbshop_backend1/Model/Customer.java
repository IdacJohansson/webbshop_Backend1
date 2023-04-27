package com.example.webbshop_backend1.Model;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name="customers")
@Data
@NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue
    private Long id;
    private String customerName;
    private String ssn;

    public Customer(String customerName, String ssn){
        this.customerName = customerName;
        this.ssn = ssn;

    }

}
