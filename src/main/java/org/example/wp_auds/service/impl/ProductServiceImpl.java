package org.example.wp_auds.service.impl;

import jakarta.transaction.Transactional;
import org.example.wp_auds.model.Category;
import org.example.wp_auds.model.Manufacturer;
import org.example.wp_auds.model.Product;
import org.example.wp_auds.model.exceptions.CategoryNotFoundException;
import org.example.wp_auds.model.exceptions.ManufacturersNotFoundException;
import org.example.wp_auds.repository.InMemory.InMemoryCategoryRepository;
import org.example.wp_auds.repository.InMemory.InMemoryManufacturerRepository;
import org.example.wp_auds.repository.InMemory.InMemoryProductRepository;
import org.example.wp_auds.repository.jpa.CategoryRepository;
import org.example.wp_auds.repository.jpa.ManufacturerRepository;
import org.example.wp_auds.repository.jpa.ProductRepository;
import org.example.wp_auds.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ManufacturerRepository manufacturerRepository;
    public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository, ManufacturerRepository manufacturerRepository){
        this.productRepository=productRepository;
        this.categoryRepository = categoryRepository;
        this.manufacturerRepository = manufacturerRepository;
    }
    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    @Transactional
    public Optional<Product> save(String name, Double price, int quantity, Long categoryId, Long manufacturerId) {
            Category category=this.categoryRepository.findById(categoryId).orElseThrow(()->new CategoryNotFoundException(categoryId));
            Manufacturer manufacturer=this.manufacturerRepository.findById(manufacturerId).orElseThrow(()->new ManufacturersNotFoundException(manufacturerId));

            this.productRepository.deleteByName(name);
            return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,manufacturer)));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
