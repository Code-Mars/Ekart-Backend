package com.ekart.cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.cart.dto.CartDTO;
import com.ekart.cart.dto.Status;
import com.ekart.cart.exception.EkartException;
import com.ekart.cart.service.CartService;

import jakarta.validation.Valid;

@RestController
@CrossOrigin
@RequestMapping("/cart")
@Validated
public class CartAPI {
    @Autowired
    private CartService cartService;
    @Autowired
    private Environment environment;

    @PostMapping("/add")
    public ResponseEntity<String> addItem(@RequestBody @Valid CartDTO item)throws EkartException{
        Integer n=cartService.addItem(item);
        return new ResponseEntity<>(environment.getProperty(n==1?"CartAPI.ITEM_ADDED":"CartAPI.ITEM_UPDATED"), HttpStatus.CREATED);
    }
    @PutMapping("/update")
    public ResponseEntity<String> updateItem(@RequestBody @Valid CartDTO item)throws EkartException{
        cartService.updateItem(item);
        return new ResponseEntity<>(environment.getProperty("CartAPI.ITEM_UPDATED"), HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteItem(@PathVariable Long id)throws EkartException{
        cartService.deleteItem(id);
        return new ResponseEntity<>(environment.getProperty("CartAPI.ITEM_DELETED"), HttpStatus.OK);
    }
    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelOrder(@PathVariable Long id)throws EkartException{
        cartService.cancelOrder(id);
        return new ResponseEntity<>(environment.getProperty("CartAPI.ITEM_CANCELED"), HttpStatus.OK);
    }
    @PostMapping("/order")
    public ResponseEntity<String> orderItems(@RequestBody List<Long> items) throws EkartException{
        cartService.orderItems(items);
        return new ResponseEntity<>(environment.getProperty("CartAPI.ITEM_ORDERED"), HttpStatus.OK);
    }
    @GetMapping("/get/{userId}/{status}")
    public ResponseEntity<List<CartDTO>> getOrders(@PathVariable Long userId, @PathVariable Status status){
        return new ResponseEntity<>(cartService.getOrders(userId, status), HttpStatus.OK);
    }
}
