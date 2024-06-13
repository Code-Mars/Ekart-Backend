package com.ekart.user.service;


import com.ekart.user.dto.LoginDTO;
import com.ekart.user.dto.UserDTO;
import com.ekart.user.exception.EkartException;

public interface UserService {
    long getNextSequenceId(String key) throws EkartException;
    public Long registerUser(UserDTO userDTO) throws EkartException;
    public UserDTO loginUser(LoginDTO loginDTO)throws EkartException;
    public void resetPassword(LoginDTO loginDTO) throws EkartException;
    Long getUserId(String email) throws EkartException; 
    UserDTO getUser(Long id) throws EkartException;
}
