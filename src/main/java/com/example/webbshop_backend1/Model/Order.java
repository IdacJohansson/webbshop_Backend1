package com.example.webbshop_backend1.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity(name = "orders")
    @Data
    @NoArgsConstructor
    public class Order {

        @Id
        @GeneratedValue
        private Long id;
        private String date;

        @ManyToOne
        @JoinColumn
        private Customer customer;

        @ManyToOne
        @JoinColumn
        private Item item; // dubbelkolla annoteringen

        public Order(String date, Item item, Customer customer){
            this.date = date;
            this.item = item;
            this.customer = customer;
        }

    }

