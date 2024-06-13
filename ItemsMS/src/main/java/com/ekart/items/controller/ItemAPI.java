package com.ekart.items.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ekart.items.dto.ItemDTO;
import com.ekart.items.exception.EkartException;
import com.ekart.items.service.ItemService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

@RestController
@CrossOrigin
@Validated
@RequestMapping("/items")
public class ItemAPI {
    @Autowired
    private ItemService itemService;
    
    @PostMapping(value = "/registerAll")
    public ResponseEntity<List<ItemDTO>> registerItem(@RequestBody @Valid List<ItemDTO> itemDTOs) throws EkartException {
        itemDTOs.stream().forEach((x)->{
           try {
            x.setId(itemService.registerItem(x));
        } catch (EkartException e) {
            e.printStackTrace();
        }
        });
        return new ResponseEntity<>(itemDTOs, HttpStatus.CREATED);       
    }
    @PostMapping(value = "/register")
    public ResponseEntity<ItemDTO> registerItem(@RequestBody @Valid ItemDTO itemDTO) throws EkartException {
        Long id=itemService.registerItem(itemDTO);
        itemDTO.setId(id);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);       
    }
    @GetMapping(value = "/get/{id}")
    public ResponseEntity<ItemDTO> getItem(@NotNull(message = "{itemid.absent}") @PathVariable Long id) throws EkartException {
        ItemDTO itemDTO = itemService.getItem(id);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ItemDTO>> getItemId() throws EkartException {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
    @GetMapping(value="/category/{term}")
    public ResponseEntity<List<ItemDTO>> getCategoryWiseItems(@PathVariable String term) {
        return new ResponseEntity<>(itemService.searchItems(term), HttpStatus.OK);
    }
    @GetMapping("/search/{term}")
    public ResponseEntity<List<ItemDTO>> searchItems(@PathVariable String term) {
        return new ResponseEntity<>(itemService.searchItems(term), HttpStatus.OK);
    }
    @GetMapping("/price/less/{price}")
    public ResponseEntity<List<ItemDTO>> getItemsPriceLess(@PathVariable Double price) {
        return new ResponseEntity<>(itemService.getItemsOnPriceLess(price), HttpStatus.OK);
    }
    @GetMapping("/price/greater/{price}")
    public ResponseEntity<List<ItemDTO>> getItemsPriceGreater(@PathVariable Double price) {
        return new ResponseEntity<>(itemService.getItemsOnPriceGreater(price), HttpStatus.OK);
    }
    @GetMapping("/price/between/{low}/{high}")
    public ResponseEntity<List<ItemDTO>> getItemsPriceBetween(@PathVariable Double low, @PathVariable Double high) {
        return new ResponseEntity<>(itemService.getItemsOnPriceBetween(low, high), HttpStatus.OK);
    }
    @GetMapping("/rating/{rating}")
    public ResponseEntity<List<ItemDTO>> getItemsOnRating(@PathVariable Double rating) {
        return new ResponseEntity<>(itemService.getItemsOnRatings(rating), HttpStatus.OK);
    }
}
