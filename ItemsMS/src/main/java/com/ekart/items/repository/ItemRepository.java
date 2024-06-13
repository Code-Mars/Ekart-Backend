package com.ekart.items.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.ekart.items.entity.Item;

public interface ItemRepository extends MongoRepository<Item, Long>{
    List<Item>findByAllIgnoreCaseTitleContainingOrDescContainingOrCategoryContaining(String title, String desc, String category);
    List<Item>findByCategory(String category);
    List<Item>findByPriceLessThanEqual(Double price);
    List<Item>findByPriceGreaterThanEqual(Double price);
    List<Item>findByPriceBetween(Double low , Double high);
    List<Item>findByRatingGreaterThanEqual(Double rating);
}
