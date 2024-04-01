package com.ekart.cart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.ekart.cart.dto.Status;
import com.ekart.cart.entity.Cart;

public interface CartRepository extends MongoRepository<Cart, Long> {
    Optional<Cart>findByUserIdAndItemIdAndStatus(Long userId, Long itemId, Status status);
    List<Cart>findByUserIdAndStatus(Long userId, Status status);
}
