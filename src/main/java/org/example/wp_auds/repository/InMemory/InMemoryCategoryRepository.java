package org.example.wp_auds.repository.InMemory;

import org.example.wp_auds.bootstrap.dataholder;
import org.example.wp_auds.model.Category;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryCategoryRepository {
    public List<Category> findAll(){
        return dataholder.categories;
    }

    public Category save (Category c){
        if (c==null || c.getName()!=null || c.getName().isEmpty()){
            return null;
        }
        dataholder.categories.removeIf(r->r.getName().equals(c.getName()));
        dataholder.categories.add(c);
        return c;
    }

    public Optional<Category> findByName(String name){
        return dataholder.categories.stream().filter(r->r.getName().equals(name)).findFirst();
    }

    public Optional<Category> findById(Long id){
        return dataholder.categories.stream().filter(r->r.getId().equals(id)).findFirst();
    }

    public List<Category> search (String text){
        return dataholder.categories.stream().filter(r->r.getName().contains(text) || r.getDescription().contains(text)).collect(Collectors.toList());
    }

    public void delete(String name){
        dataholder.categories.removeIf(r->r.getName().equals(name));
    }
}
