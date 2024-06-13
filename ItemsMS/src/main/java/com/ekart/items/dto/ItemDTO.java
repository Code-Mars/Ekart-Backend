package com.ekart.items.dto;

import com.ekart.items.entity.Item;
import java.util.Objects;

public class ItemDTO {
    private Long id;
    private String title;
    private String desc;
    private Double price;
    private Double rating; 
    private String category;
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return this.desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
    
    public Double getPrice() {
        return this.price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    

    public String getCategory() {
        return this.category;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }
    public static ItemDTO getDTO(Item item) {
        ItemDTO itemDTO= new ItemDTO();
        itemDTO.setTitle(item.getTitle());
        itemDTO.setDesc(item.getDesc());
        itemDTO.setId(item.getId());
        itemDTO.setPrice(item.getPrice());
        itemDTO.setCategory(item.getCategory());
        itemDTO.setRating(item.getRating());
        return itemDTO;
    }
    public static Item getEntity(ItemDTO itemDTO) {
        Item item= new Item();
        item.setTitle(itemDTO.getTitle());
        item.setDesc(itemDTO.getDesc());
        item.setId(itemDTO.getId());
        item.setPrice(itemDTO.getPrice());
        item.setCategory(itemDTO.getCategory());
        item.setRating(itemDTO.getRating());
        return item;
    }
    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ItemDTO)) {
            return false;
        }
        ItemDTO itemDTO = (ItemDTO) o;
        return Objects.equals(id, itemDTO.id) && Objects.equals(title, itemDTO.title) && Objects.equals(desc, itemDTO.desc) && Objects.equals(price, itemDTO.price)  && Objects.equals(category, itemDTO.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, desc, price, category);
    }
    
    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", title='" + getTitle() + "'" +
            ", desc='" + getDesc() + "'" +
            ", price='" + getPrice() + "'" +
            ", category='" + getCategory() + "'" +
            "}";
    }

    
    
    
}
