package org.example.wp_auds.service;

import org.example.wp_auds.model.Manufacturer;
import org.springframework.context.annotation.Bean;

import java.util.List;
import java.util.Optional;


public interface ManufacturerService {
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);

    Optional<Manufacturer> save(String name,String address);

    void deleteById(Long id);
}
