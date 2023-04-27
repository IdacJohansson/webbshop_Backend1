package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Model.Item;
import com.example.webbshop_backend1.Model.Order;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import com.example.webbshop_backend1.Repo.OrderRepo;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    private final CustomerRepo customerRepo;
    private final ItemsRepo itemsRepo;
    private final OrderRepo orderRepo;

    public OrderController(OrderRepo orderRepo, CustomerRepo customerRepo, ItemsRepo itemsRepo) {
        this.customerRepo = customerRepo;
        this.itemsRepo = itemsRepo;
        this.orderRepo = orderRepo;
    }

    // curl http://localhost:8080/items/buy -H "Content-Type:application/json" -d "{\"customerId\":\"1\", \"itemsId\":\"3\"}" -v
    // Denna endpoint gör ett nytt köp för en specifik kund och en specifik vara, baserat på id
    @PostMapping("items/buy")
    public List<Order> customerBuy(@RequestBody Map<String, Long> body) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateString = date.format(formatter);

        Long c = body.get("customerId");
        Long i = body.get("itemsId");

        Customer customer = customerRepo.findById(c).orElse(null);
        Item item = itemsRepo.findById(i).orElse(null);

        if (customer == null || item == null) {
            System.out.println("Missing ID");
            return new ArrayList<>();
        }

        Order newOrder = new Order(dateString, item, customer);
        orderRepo.save(newOrder);
        return orderRepo.findAll();
    }


    @RequestMapping("orders")
    public List<Order> getAllOrders() {
        return orderRepo.findAll();
    }

    @RequestMapping("orders/{customerId}")
    public String findOrdersByCustomerId(@PathVariable Long id) {
        List<Order> orderFromCustomer = new ArrayList<>();
        String name = null;
        while(true) {
            if (orderRepo != null) {
                Order o = orderRepo.findById(id).orElse(null);
                if (o != null) {
                    orderFromCustomer.add(o);
                    name = o.getCustomer().getCustomerName();
                }
            }

            return "Orders made by " + name + orderFromCustomer;
        }
    }
}
