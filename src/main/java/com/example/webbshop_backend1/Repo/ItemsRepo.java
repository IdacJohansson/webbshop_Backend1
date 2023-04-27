package com.example.webbshop_backend1.Repo;


import com.example.webbshop_backend1.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemsRepo extends JpaRepository<Item, Long> {

}
