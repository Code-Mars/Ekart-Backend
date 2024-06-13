package com.ekart.user.dto;


import java.util.List;

import com.ekart.user.entity.User;

public class UserDTO {
    private Long id;
    private String name;
    private String email;
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
    public static UserDTO entityToDTO(User user) {
        UserDTO userDTO= new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setId(user.getId());
        userDTO.setMobile(user.getMobile());
        userDTO.setPassword(user.getPassword());
        userDTO.setWishlist(user.getWishlist());
        return userDTO;
    }
    public static User getEntity(UserDTO userDTO) {
        User user= new User();
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setId(userDTO.getId());
        user.setMobile(userDTO.getMobile());
        user.setPassword(userDTO.getPassword());
        user.setWishlist(userDTO.getWishlist());
        return user;
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((email == null) ? 0 : email.hashCode());
        result = prime * result + ((mobile == null) ? 0 : mobile.hashCode());
        result = prime * result + ((password == null) ? 0 : password.hashCode());
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
        UserDTO other = (UserDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (email == null) {
            if (other.email != null)
                return false;
        } else if (!email.equals(other.email))
            return false;
        if (mobile == null) {
            if (other.mobile != null)
                return false;
        } else if (!mobile.equals(other.mobile))
            return false;
        if (password == null) {
            if (other.password != null)
                return false;
        } else if (!password.equals(other.password))
            return false;
        return true;
    }
    
    
    
}
