package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.exception.NotSavedCustomerException;
import com.example.webbshop_backend1.exception.NotFoundCustomerException;
import com.example.webbshop_backend1.service.CustomerService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController()
@RequestMapping("/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    //http://localhost:8080/customers (Denna returnerar alla kunder)
    @GetMapping("/all")
    public List<Customer> getAllCustomers() throws NotFoundCustomerException {
        return customerService.findAllCustomer();
    }


    //curl http://localhost:8080/customers/add -H "Content-Type:application/json" -d "{\"name\":\"Johnny Bravo\", \"ssn\":\"198707226512\"}" -v
    @PostMapping("/add")
    public boolean addCustomer(@RequestBody Customer newCustomer) throws NotSavedCustomerException {
        return customerService.save(newCustomer);
    }

    @RequestMapping("/{id}")
    public Customer findCustomerById(@PathVariable("id") Long id) throws NotFoundCustomerException {
        return customerService.findCustomerById(id);
    }
}
