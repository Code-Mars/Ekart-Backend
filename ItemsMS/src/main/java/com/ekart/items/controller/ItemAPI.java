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
    
    @PostMapping(value = "/register")
    public ResponseEntity<ItemDTO> registerItem(@RequestBody @Valid ItemDTO itemDTO) throws EkartException {
        Long id=itemService.registerItem(itemDTO);
        itemDTO.setId(id);
        return new ResponseEntity<>(itemDTO, HttpStatus.CREATED);       
    }
    @PostMapping(value = "/get")
    public ResponseEntity<ItemDTO> getItem(@NotNull(message = "{itemid.absent}") @RequestBody Long id) throws EkartException {
        ItemDTO itemDTO = itemService.getItem(id);
        return new ResponseEntity<>(itemDTO, HttpStatus.OK);
    }
    @GetMapping(value = "/getAll")
    public ResponseEntity<List<ItemDTO>> getItemId() throws EkartException {
        return new ResponseEntity<>(itemService.getAllItems(), HttpStatus.OK);
    }
    
    @GetMapping("/search/{term}")
    public ResponseEntity<List<ItemDTO>> searchItems(@PathVariable String term) {
        return new ResponseEntity<>(itemService.searchItems(term), HttpStatus.OK);
    }
}