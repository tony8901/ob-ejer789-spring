package com.obejer789spring.services;

import com.obejer789spring.entities.Laptop;
import com.obejer789spring.repositories.LaptopRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LaptopService {

    private final LaptopRepository laptopRepository;

    public LaptopService(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }

    public ResponseEntity<List<Laptop>> findAll() {
        Optional<List<Laptop>> optionalLaptops = Optional.of(laptopRepository.findAll());
        return optionalLaptops.map(laptops -> new ResponseEntity<>(laptops, HttpStatus.OK)).orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<Laptop> findById(Long id){
        Optional<Laptop> laptop = laptopRepository.findById(id);
        return laptop.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElse(ResponseEntity.noContent().build());
    }

    public ResponseEntity<Laptop> save(Laptop laptop){
        Optional<Laptop> optionalLaptop = Optional.of(laptopRepository.save(laptop));
        return optionalLaptop.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElse(ResponseEntity.badRequest().build());
    }

    public ResponseEntity<Laptop> update(Laptop laptop){
        if(laptop.getId() == null){
            return ResponseEntity.badRequest().build();
        }
        if(!laptopRepository.existsById(laptop.getId())){
            return ResponseEntity.notFound().build();
        }
        return save(laptop);
    }

    public ResponseEntity<HttpStatus> delete(Long id){
        if(!laptopRepository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<HttpStatus> deleteAll(){
        laptopRepository.deleteAll();
        return ResponseEntity.ok().build();
    }
}
