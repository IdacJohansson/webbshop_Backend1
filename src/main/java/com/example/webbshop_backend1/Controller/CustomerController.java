package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customers;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerRepo customerRepo;

    public CustomerController(CustomerRepo customerRepo){
        this.customerRepo = customerRepo;
    }

    //http://localhost:8080/customers (Denna returnerar alla kunder)
    @RequestMapping("customers")
    public List<Customers> getAllCustomers() {
        return customerRepo.findAll();
    }

    //http://localhost:8080/customers (Denna adderar en kund)
    @RequestMapping("customers/add")
    public String addCustomerById(@PathParam("id") Long id, @PathParam("name") String name, @PathParam("ssn") String ssn) {
        customerRepo.save(new Customers(name, ssn));
        return "customer " + name + " added";
    }

    //TODO
    //http://localhost:8080/customers (Denna returnerar en kund mha @PathParam)
    @RequestMapping("customers/{id}")
    public String findCustomerById(@PathVariable Long id) {
        String name = null;
        if (customerRepo != null) {
            Customers C = customerRepo.findById(id).orElse(null);
            if (C != null) {
                name = C.getCustomerName();
            }
        }
        return "Customer with id " + id + " is " + name;
    }

    //http://localhost:8080/customers (Denna tar bort en kund vid behov)
    @RequestMapping("customers/delete/{id}")
    public String deleteCustomer(@PathVariable Long id) {
        customerRepo.deleteById(id);
        return "customer " + id + " removed";
    }
}
