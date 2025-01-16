package org.example.wp_auds.repository.InMemory;

import org.example.wp_auds.bootstrap.dataholder;
import org.example.wp_auds.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {
    public List<Manufacturer> findAll(){
        return dataholder.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id){
        return dataholder.manufacturers.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Manufacturer> save(String name, String address){
        Manufacturer manufacturer=new Manufacturer(name,address);
        dataholder.manufacturers.add(manufacturer);
        return Optional.of(manufacturer);
    }

}
