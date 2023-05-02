package com.example.webbshop_backend1.service;


import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.exception.NotSavedCustomerException;
import com.example.webbshop_backend1.exception.NotFoundCustomerException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CustomerService {

    @Autowired
    private CustomerRepo dao;


    public CustomerService(CustomerRepo dao) {
        this.dao = dao;
    }

    public List<Customer> findAllCustomer() throws NotFoundCustomerException {
        List<Customer> allCustomer = dao.findAll();

        if (allCustomer.isEmpty()) {
            throw new NotFoundCustomerException("No customers find!");
        } else {
            return allCustomer;
        }
    }

    public Boolean save(Customer newCustomer) throws NotSavedCustomerException {
        Customer savedCustomer = dao.save(newCustomer);
        if (newCustomer.equals(savedCustomer)) {
            return true;
        } else {
            throw new NotSavedCustomerException(String.format("Something bad happen during saving customer= %s ", newCustomer));
        }
    }


    public Customer findCustomerById(Long id) throws NotFoundCustomerException {

        Optional<Customer> optionalCustomer = dao.findById(id);

        if (optionalCustomer.isPresent()) {
            log.info("Customer found by id={} ", id);
            return optionalCustomer.get();
        } else
            throw new NotFoundCustomerException(String.format("There is no customer with id = %s", id));
    }
}
