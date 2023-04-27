package com.example.webbshop_backend1;

import com.example.webbshop_backend1.Model.Customer;
import com.example.webbshop_backend1.Model.Item;
import com.example.webbshop_backend1.Model.Order;
import com.example.webbshop_backend1.Repo.CustomerRepo;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import com.example.webbshop_backend1.Repo.OrderRepo;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WebbshopBackend1Application {

    public static void main(String[] args) {
        SpringApplication.run(WebbshopBackend1Application.class, args);
    }


    @Bean
    public CommandLineRunner pojo(CustomerRepo customerRepo, ItemsRepo itemsRepo, OrderRepo orderRepo){
        return args -> {

            Customer c1 = new Customer("Anna Andersson", "196602071122");
            Customer c2 = new Customer("Bella Bengtsson", "198610096655");
            Customer c3 = new Customer("Cecilia Carlsson", "199605083344");

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);

            Item i1 = new Item("T-shirt", 200);
            Item i2 = new Item("Hoodie", 300);
            Item i3 = new Item("Shorts", 100);

            itemsRepo.save(i1);
            itemsRepo.save(i2);
            itemsRepo.save(i3);

            Order o1 = new Order("2023-04-17", i1, c1);
            Order o2 = new Order("2023-04-15", i2, c2);
            Order o3 = new Order("2023-04-19", i3, c3);

            orderRepo.save(o1);
            orderRepo.save(o2);
            orderRepo.save(o3);


        };
    }

}
