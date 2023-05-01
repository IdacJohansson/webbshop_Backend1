package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.exception.NotFoundCustomerException;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo) {
        this.customerRepo = customerRepo;
    }

    //http://localhost:8080/customers (Denna returnerar alla kunder)
    @RequestMapping("customers")
    public List<Customer> getAllCustomers() throws NotFoundCustomerException {
        List<Customer> allCustomers = customerRepo.findAll();
        if (allCustomers.isEmpty()) {
            throw new NotFoundCustomerException("No customers find!");
        } else
            return allCustomers;
    }


    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"name\":\"Johnny Bravo\", \"ssn\":\"198707226512\"}" -v
    @PostMapping("customers/add")
    public List<Customer> addCustomers(@RequestBody Customer c) {
        customerRepo.save(c);
        return customerRepo.findAll();
    }

    @RequestMapping("customers/{id}")
    public String findCustomerById(@PathVariable Long id) {
        String name = null;
        if (customerRepo != null) {
            Customer C = customerRepo.findById(id).orElse(null);
            if (C != null) {
                name = C.getCustomerName();
            }
        }
        return "Customer with id number " + id + " is " + name;
    }
}
