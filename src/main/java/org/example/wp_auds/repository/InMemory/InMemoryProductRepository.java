package org.example.wp_auds.repository.InMemory;

import org.example.wp_auds.bootstrap.dataholder;
import org.example.wp_auds.model.Category;
import org.example.wp_auds.model.Manufacturer;
import org.example.wp_auds.model.Product;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryProductRepository {

    public List<Product> findAll(){
        return dataholder.products;
    }

    public Optional<Product> findById(long id){
        return dataholder.products.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public Optional<Product> save(String name, Double price, int quantity, Category category, Manufacturer manufacturer){
        dataholder.products.removeIf(r->r.getName().equals(name));
        Product product=new Product(name,price,quantity,category,manufacturer);
        dataholder.products.add(product);
        return Optional.of(product);
    }

    public void deleteById(Long id){
        dataholder.products.removeIf(r->r.getId().equals(id));
    }

    public Optional<Product> findByName(String name){
        return dataholder.products.stream().filter(r->r.getName().equals(name)).findFirst();
    }
}
