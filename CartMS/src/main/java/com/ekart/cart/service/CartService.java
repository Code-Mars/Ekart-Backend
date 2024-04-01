package com.ekart.cart.service;

import java.util.List;

import com.ekart.cart.dto.CartDTO;
import com.ekart.cart.dto.Status;
import com.ekart.cart.exception.EkartException;

public interface CartService {
    public long getNextSequenceId(String key) throws EkartException;
    public Integer addItem(CartDTO item)throws EkartException;
    public void updateItem(CartDTO item)throws EkartException;
    public void deleteItem(Long id)throws EkartException;
    public void cancelOrder(Long id)throws EkartException;
    public void orderItems(List<Long> items) throws EkartException;
    public List<CartDTO> getOrders(Long userId, Status status);
}
