package com.ekart.user.entity;


import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "users")
public class User {
    @Id
    private Long id;
    private String name;
    @Indexed(unique = true)
    private String email;
    @Indexed(unique = true)
    private String mobile;
    private String password;
    private List<Long> wishlist;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public List<Long> getWishlist() {
        return wishlist;
    }
    public void setWishlist(List<Long> wishlist) {
        this.wishlist = wishlist;
    }
    
}