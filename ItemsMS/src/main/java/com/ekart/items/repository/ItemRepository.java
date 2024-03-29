package com.ekart.items.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ekart.items.entity.Item;

public interface ItemRepository extends MongoRepository<Item, Long>{
    List<Item>findByAllIgnoreCaseTitleContainingOrDescContainingOrCategoryContaining(String title, String desc, String category);
}
