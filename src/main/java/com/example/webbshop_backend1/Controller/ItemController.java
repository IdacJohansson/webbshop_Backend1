package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Item;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ItemController {

    private final ItemsRepo itemsRepo;

    public ItemController(ItemsRepo itemsRepo){
        this.itemsRepo= itemsRepo;
    }

    // http://localhost:8080/items (Denna returnerar alla varor)
    @RequestMapping("items")
    public List<Item> getAllItems(){
        return itemsRepo.findAll();
    }

    //curl http://localhost:8080/items/add -H "Content-Type:application/json" -d "{\"name\":\"Green beret\", \"price\":\"324\"}" -v
    @PostMapping("items/add")
    public List<Item> addItems(@RequestBody Item i) {
        itemsRepo.save(i);
        return itemsRepo.findAll();
    }

    @RequestMapping("items/{id}")
    public String findCustomerById(@PathVariable Long id) {
        String name = null;
        if (itemsRepo != null) {
            Item i = itemsRepo.findById(id).orElse(null);
            if (i != null) {
                name = i.getName();
            }
        }
        return "Item with id number " + id + " is " + name;
    }
}
