package com.example.webbshop_backend1.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Entity(name="customers")
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(customerName, customer.customerName) && Objects.equals(ssn, customer.ssn);
    }
}
