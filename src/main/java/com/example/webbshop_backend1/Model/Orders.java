package com.example.webbshop_backend1.Model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


    @Entity
    @Data
    @NoArgsConstructor
    public class Orders {

        @Id
        @GeneratedValue
        private Long id;
        private String date;


        public  Orders(String date){
            this.date = date;
        }


        @ManyToOne
        @JoinColumn
        private Customers customers;

        @ManyToOne
        @JoinColumn
        private Items items; // dubbelkolla annoteringen


        public Orders(String date, Items items, Customers customers){
            this.date = date;
            this.items = items;
            this.customers = customers;
        }



}

