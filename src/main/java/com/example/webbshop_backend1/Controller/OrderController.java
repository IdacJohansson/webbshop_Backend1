package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {

    private final CustomerRepo customerRepo;
    private final ItemsRepo itemsRepo;


    public OrderController(CustomerRepo customerRepo, ItemsRepo itemsRepo ){
        this.customerRepo = customerRepo;
        this.itemsRepo = itemsRepo;
    }
}
