package com.obejer789spring.controllers;

import com.obejer789spring.entities.Laptop;
import com.obejer789spring.services.LaptopService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {

    private final LaptopService laptopService;

    public LaptopController(LaptopService laptopService) {
        this.laptopService = laptopService;
    }

    @GetMapping
    public ResponseEntity<List<Laptop>> findALl(){
        return laptopService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Laptop> findById(@PathVariable Long id){
        return laptopService.findById(id);
    }

    @PostMapping
    public ResponseEntity<Laptop> save(@RequestBody Laptop laptop){
        return laptopService.save(laptop);
    }

    @PutMapping
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        return laptopService.update(laptop);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable Long id){
        return laptopService.delete(id);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<HttpStatus> deleteAll(){
        return laptopService.deleteAll();
    }


}
