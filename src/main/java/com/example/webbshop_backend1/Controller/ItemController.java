package com.example.webbshop_backend1.Controller;

import ch.qos.logback.classic.Logger;
import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Repo.ItemsRepo;

import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ItemController {

    private static final Logger log = (Logger) LoggerFactory.getLogger(ItemController.class);

    private final ItemsRepo itemsRepo;

    public ItemController(ItemsRepo itemsRepo){
        this.itemsRepo= itemsRepo;
    }


    // http://localhost:8080/items (Denna returnerar alla varor)
    @RequestMapping("items")
    public List<Items> getAllItems(){
        return itemsRepo.findAll();
    }


    @RequestMapping("items/{id}")
    public String findCustomerById(@PathVariable Long id) {
        String name = null;
        if (itemsRepo != null) {
            Items i = itemsRepo.findById(id).orElse(null);
            if (i != null) {
                name = i.getName();
            }
        }

        return "Item with id number " + id + " is " + name;
    }

    //curl http://localhost:8080/items/add -H "Content-Type:application/json" -d "{\"name\":\"Green beret\", \"price\":\"324\"}" -v
    @PostMapping("items/add")
    public List<Items> addItems(@RequestBody Items i) {
        itemsRepo.save(i);
        log.error("Item " + i.getName() + " was added to database");
        return itemsRepo.findAll();
    }

}


