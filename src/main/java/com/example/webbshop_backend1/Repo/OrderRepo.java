package com.example.webbshop_backend1.Repo;

import com.example.webbshop_backend1.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepo extends JpaRepository<Orders, Long> {

    public List<Orders> findAllByCustomersId(long id);
}
