package com.example.webbshop_backend1.Repo;


import com.example.webbshop_backend1.Model.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepo extends JpaRepository<Customers, Long> {
}
