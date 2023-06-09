package com.example.webbshop_backend1.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Data
    @NoArgsConstructor
    public class Items {

        @Id
        @GeneratedValue
        private Long id;
        private String name;
        private int price;

        public Items(String name, int price){
            this.name = name;
            this.price = price;

        }
}
