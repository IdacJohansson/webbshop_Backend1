package com.example.webbshop_backend1.Repo;

import com.example.webbshop_backend1.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepo extends JpaRepository<Orders, Long> {
}
