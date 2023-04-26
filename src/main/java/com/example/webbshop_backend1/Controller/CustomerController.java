package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Repo.CustomerRepo;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }
}
