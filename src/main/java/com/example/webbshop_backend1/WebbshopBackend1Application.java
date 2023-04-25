package com.example.webbshop_backend1;

import com.example.webbshop_backend1.Model.Customers;
import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Model.Orders;
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

            Customers c1 = new Customers("Anna Andersson", "196602071122");
            Customers c2 = new Customers("Bella Bengtsson", "198610096655");
            Customers c3 = new Customers("Cecilia Carlsson", "199605083344");

            customerRepo.save(c1);
            customerRepo.save(c2);
            customerRepo.save(c3);

            Items i1 = new Items("T-shirt", 200);
            Items i2 = new Items("Hoodie", 300);
            Items i3 = new Items("Shorts", 100);

            itemsRepo.save(i1);
            itemsRepo.save(i2);
            itemsRepo.save(i3);

            Orders o1 = new Orders("2023-04-17", i1, c1);
            Orders o2 = new Orders("2023-04-15", i2, c2);
            Orders o3 = new Orders("2023-04-19", i3, c3);

            orderRepo.save(o1);
            orderRepo.save(o2);
            orderRepo.save(o3);


        };
    }

}
