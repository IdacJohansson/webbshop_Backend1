package com.example.webbshop_backend1.Controller;

import com.example.webbshop_backend1.Model.Items;
import com.example.webbshop_backend1.Repo.ItemsRepo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ItemController {

    private final ItemsRepo itemsRepo;

    public ItemController(ItemsRepo itemsRepo){
        this.itemsRepo= itemsRepo;
    }






    // http://localhost:8080/items (Denna returnerar alla varor)
    @RequestMapping("items")
    public List<Items> getAllItems(){
        return itemsRepo.findAll();
    }

    //curl http://localhost:8080/items/add -H "Content-Type:application/json" -d "{\"name\":\"Green beret\", \"price\":\"324\"}" -v
    @PostMapping("items/add")
    public List<Items> addItems(@RequestBody Items i) {
        itemsRepo.save(i);
        return itemsRepo.findAll();
    }
}


