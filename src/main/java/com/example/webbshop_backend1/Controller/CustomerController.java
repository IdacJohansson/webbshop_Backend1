package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customers;
import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    @RequestMapping("customers")
    public List<Customers> getAllCustomers() {
        return customerRepo.findAll();
    }


    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"name\":\"Johnny Bravo\", \"ssn\":\"199007226512\"}" -v
    @PostMapping("customers/add")
    public List<Customers> addCustomers(@RequestBody Customers c) {
        customerRepo.save(c);
        return customerRepo.findAll();
    }
}
