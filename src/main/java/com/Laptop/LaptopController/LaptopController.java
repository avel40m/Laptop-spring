package com.Laptop.LaptopController;

import com.Laptop.Laptop.Laptop;
import com.Laptop.LaptopRepository.LaptopRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LaptopController {
    private LaptopRepository laptopRepository;

    private final static Logger log = LoggerFactory.getLogger(LaptopController.class);

    public LaptopController(LaptopRepository laptopRepository) {
        this.laptopRepository = laptopRepository;
    }
    @GetMapping("/api/laptop")
    public List<Laptop> findAll(){
        return laptopRepository.findAll();
    }

    @GetMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> findOneLaptop(@PathVariable Long id){
        Optional<Laptop> lapOpt = laptopRepository.findById(id);
        if (lapOpt.isPresent())
            return ResponseEntity.ok(lapOpt.get());
        else
            return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/laptop")
    public ResponseEntity<Laptop> create(@RequestBody Laptop laptop){
        if (laptop.getId() != null){
            log.warn("Trying to create a laptop with id");
            return ResponseEntity.badRequest().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/api/laptop")
    public ResponseEntity<Laptop> update(@RequestBody Laptop laptop){
        if (laptop.getId() == null){
            log.warn("Trying to update a not exist laptop");
            return ResponseEntity.badRequest().build();
        }
        if (!laptopRepository.existsById(laptop.getId())){
            log.warn("Trying to update a not existing id");
            return ResponseEntity.notFound().build();
        }
        Laptop result = laptopRepository.save(laptop);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/api/laptop/{id}")
    public ResponseEntity<Laptop> delete(@PathVariable Long id){
        if (id == null){
            log.error("Not exist id");
            return ResponseEntity.badRequest().build();
        }
        laptopRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/api/laptop")
    public ResponseEntity<Laptop> deleteAll(){
        log.info("Delete all laptop");
        laptopRepository.deleteAll();
        return ResponseEntity.noContent().build();
    }
}
