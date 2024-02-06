package com.example.demo.controller;

import com.example.demo.dao.LaptopDao;
import com.example.demo.model.Laptop;
import com.example.demo.service.LaptopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/laptops")

public class LaptopController {

    private final LaptopService service;

    @PostMapping("/insert")
    public String insertLaptop(@RequestBody Laptop model){
        return service.insert(model);
    }

    @GetMapping("/getAll")
    public List<Laptop> getAll(){
        return service.getAll();
    }

    @GetMapping("/getById")
    public Laptop getById(@RequestParam Long id){
        return service.getById(id);
    }

    @PutMapping("/update")
    public String update(@RequestParam Long id, @RequestBody Laptop model){
        return service.update(id,model);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteLaptop(@PathVariable Long id){
        return service.delete(id);
    }

}
