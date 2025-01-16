package org.example.wp_auds.web.controller;

import org.example.wp_auds.service.ManufacturerService;
import org.example.wp_auds.model.Manufacturer;
import org.springframework.boot.logging.log4j2.ExtendedWhitespaceThrowablePatternConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {


    private final ManufacturerService manufacturerService;

    //zosto ima greska ovde nemam pojma i nemam dovolno nervi da se zamaram :)
    public ManufacturerController(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public List<Manufacturer> findAll(){
        return this.manufacturerService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<Manufacturer> findById(@PathVariable Long id){
        return this.manufacturerService.findById(id).map(manufacturer -> ResponseEntity.ok().body(manufacturer)).orElseGet(() ->ResponseEntity.notFound().build());
    }


    // ako ne biva probaj so responseEntity
    @PostMapping("/add")
    public Optional<Manufacturer> save(@RequestParam String name, @RequestParam String address){
        return this.manufacturerService.save(name,address);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable Long id){
        this.manufacturerService.deleteById(id);
        if (this.manufacturerService.findById(id).isEmpty()) return ResponseEntity.ok().build();
        return ResponseEntity.badRequest().build();
    }
}
