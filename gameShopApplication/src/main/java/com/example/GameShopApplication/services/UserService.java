package com.example.GameShopApplication.services;
import com.example.GameShopApplication.domain.dto.UserLoginDto;
import com.example.GameShopApplication.domain.dto.UserRegisterDto;

public interface UserService {

    String registerUser(UserRegisterDto userRegisterDto);

    String loginUser(UserLoginDto userLoginDto);

    String logout();
}
