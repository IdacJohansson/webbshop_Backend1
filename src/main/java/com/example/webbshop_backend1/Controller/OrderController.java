package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Customers;
import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Model.Orders;
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

    private final OrderRepo orderRepo;


    public OrderController(OrderRepo orderRepo, CustomerRepo customerRepo, ItemsRepo itemsRepo) {
        this.customerRepo = customerRepo;
        this.itemsRepo = itemsRepo;
        this.orderRepo = orderRepo;
    }



    // curl http://localhost:8080/items/buy -H "Content-Type:application/json" -d "{\"customerId\":\"1\", \"itemsId\":\"3\"}" -v

    // Denna endpoint gör ett nytt köp för en specifik kund och en specifik vara, baserat på id

    @PostMapping("items/buy")
    public List<Orders> customerBuy(@RequestBody Map<String, Long> body) {
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String dateString = date.format(formatter);

        Long c = body.get("customerId");
        Long i = body.get("itemsId");

        Customers customer = customerRepo.findById(c).orElse(null);
        Items item = itemsRepo.findById(i).orElse(null);

        if (customer == null || item == null) {
            System.out.println("Missing ID");
            return new ArrayList<>();
        }

        Orders newOrder = new Orders(dateString, item, customer);
        orderRepo.save(newOrder);
        return orderRepo.findAll();
    }


    @RequestMapping("orders")
    public List<Orders> getAllOrders() {
        return orderRepo.findAll();
    }

    @RequestMapping("orders/{customerId}")
    public String findOrdersByCustomerId(@PathVariable Long id) {
        List<Orders> ordersFromCustomer = new ArrayList<>();
        String name = null;
        while(true) {
            if (orderRepo != null) {
                Orders o = orderRepo.findById(id).orElse(null);
                if (o != null) {
                    ordersFromCustomer.add(o);
                    name = o.getCustomers().getCustomerName();
                }
            }

            return "Orders made by " + name + ordersFromCustomer;
        }
    }

    // http://localhost:8080/orders/{customerId} (Denna returnerar alla köp för en kund baserat
    //på kundens id)
    public List<Orders> getCustomersOrders(long id){
        return orderRepo.findAllByCustomersId(id);
    }

    @RequestMapping("orders/{customerId}")
    public List<Orders> ordersByCustomersId(@PathVariable long customerId){
        return getCustomersOrders(customerId);
    }








}
