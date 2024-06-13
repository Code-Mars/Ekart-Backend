package com.ekart.cart.dto;

import java.time.LocalDate;

import com.ekart.cart.entity.Cart;

public class CartDTO {
    private Long id;
    private Long userId;
    private Long itemId;
    private Integer quantity;
    private Status status;
    private LocalDate date;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getUserId() {
        return userId;
    }
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public Long getItemId() {
        return itemId;
    }
    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public LocalDate getDate() {
        return date;
    }
    public void setDate(LocalDate date) {
        this.date = date;
    }
    
    public static CartDTO toDTO(Cart cart) {
        CartDTO dto = new CartDTO();
        dto.setId(cart.getId());
        dto.setUserId(cart.getUserId());
        dto.setItemId(cart.getItemId());
        dto.setQuantity(cart.getQuantity());
        dto.setStatus(cart.getStatus());
        dto.setDate(cart.getDate());
        return dto;
    }

    public static Cart toEntity(CartDTO dto) {
        Cart entity = new Cart();
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setItemId(dto.getItemId());
        entity.setQuantity(dto.getQuantity());
        entity.setStatus(dto.getStatus());
        entity.setDate(dto.getDate());
        return entity;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((itemId == null) ? 0 : itemId.hashCode());
        result = prime * result + ((quantity == null) ? 0 : quantity.hashCode());
        result = prime * result + ((status == null) ? 0 : status.hashCode());
        result = prime * result + ((date == null) ? 0 : date.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CartDTO other = (CartDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (itemId == null) {
            if (other.itemId != null)
                return false;
        } else if (!itemId.equals(other.itemId))
            return false;
        if (quantity == null) {
            if (other.quantity != null)
                return false;
        } else if (!quantity.equals(other.quantity))
            return false;
        if (status != other.status)
            return false;
        if (date == null) {
            if (other.date != null)
                return false;
        } else if (!date.equals(other.date))
            return false;
        return true;
    }
    
}
