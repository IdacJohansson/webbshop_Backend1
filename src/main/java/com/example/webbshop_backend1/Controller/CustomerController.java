package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customers;

import com.example.webbshop_backend1.Repo.CustomerRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
public class CustomerController {

    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    //http://localhost:8080/customers (Denna returnerar alla kunder)
    @RequestMapping("customers")
    public List<Customers> getAllCustomers() {
        return customerRepo.findAll();
    }

    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"customerName\":\"Johnny Bravo\", \"ssn\":\"198707226512\"}" -v
    @PostMapping("customers/add")
    public List<Customers> addCustomers(@RequestBody Customers c) {
        customerRepo.save(c);
        log.error("Customer " + c.getCustomerName() + " was added");
        return customerRepo.findAll();
    }

    @RequestMapping("customers/{id}")
    public String findCustomerById(@PathVariable Long id) {
        String name = null;
        if (customerRepo != null) {
            Customers C = customerRepo.findById(id).orElse(null);
            if (C != null) {
                name = C.getCustomerName();
            }
        }
        return "Customer with id number " + id + " is " + name;
    }
}
