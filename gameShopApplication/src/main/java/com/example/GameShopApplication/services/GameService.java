package com.example.GameShopApplication.services;
import com.example.GameShopApplication.domain.dto.AddGameDto;
import com.example.GameShopApplication.domain.dto.UserDto;

public interface GameService {

    String addGame(AddGameDto addGameDto);

    void setLoggedUser(UserDto userDto);

    boolean checkIfUserIsAdmin(UserDto userDto);

    String deleteGame(Long id);
}
