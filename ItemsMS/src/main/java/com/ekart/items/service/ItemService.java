package com.ekart.items.service;

import java.util.List;

import com.ekart.items.dto.ItemDTO;
import com.ekart.items.exception.EkartException;

public interface ItemService {
    long getNextSequenceId(String key) throws EkartException;
    public Long registerItem(ItemDTO itemDTO) throws EkartException;
    public ItemDTO getItem(Long id) throws EkartException;
    public List<ItemDTO> getAllItems();
    public List<ItemDTO>searchItems(String term);
}
