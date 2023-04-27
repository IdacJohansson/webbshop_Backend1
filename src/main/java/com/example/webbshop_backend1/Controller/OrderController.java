package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customers;
import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Model.Orders;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import com.example.webbshop_backend1.Repo.OrderRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    private final CustomerRepo customerRepo;
    private final ItemsRepo itemsRepo;


    public OrderController(OrderRepo orderRepo, CustomerRepo customerRepo, ItemsRepo itemsRepo) {
        this.customerRepo = customerRepo;
        this.itemsRepo = itemsRepo;
    }

    // (Denna endpoint gör ett nytt köp för en specifik kund och en specifik vara, baserat på id).
    @PostMapping("items/buy")
    public List<Orders> customerBuy(@RequestBody Items i, @RequestBody Customers c) {
        orderRepo.save(new Orders(i, c));
        return orderRepo.findAll();
    }

    @RequestMapping("orders")
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }


}
