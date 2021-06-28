package com.example.GameShopApplication.services;
import com.example.GameShopApplication.domain.dto.UserDto;
import com.example.GameShopApplication.domain.dto.UserLoginDto;
import com.example.GameShopApplication.domain.dto.UserRegisterDto;
import com.example.GameShopApplication.domain.entities.Role;
import com.example.GameShopApplication.domain.entities.User;
import com.example.GameShopApplication.repositories.UserRepository;
import com.example.GameShopApplication.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final GameService gameService;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private UserDto loggedUser;

    @Autowired
    public UserServiceImpl(GameService gameService, ValidatorUtil validatorUtil, ModelMapper modelMapper, UserRepository userRepository) {
        this.gameService = gameService;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public String registerUser(UserRegisterDto userRegisterDto) {
        StringBuilder sb = new StringBuilder();

        if (!userRegisterDto.getPassword().equals(userRegisterDto.getConfirmPassword())) {
            sb.append("Passwords don`t match");
        } else if (this.validatorUtil.isValid(userRegisterDto)) {
            User user = this.modelMapper.map(userRegisterDto, User.class);
            if (this.userRepository.count() == 0) {
                user.setRole(Role.ADMIN);
            } else {
                user.setRole(Role.USER);
            }
            sb.append(String.format("%s was registered%n", userRegisterDto.getFullName()));
            this.userRepository.saveAndFlush(user);
        } else {
            this.validatorUtil.violations(userRegisterDto)
                    .forEach(error -> sb.append(String.format("%s%n", error.getMessage())));
        }

        return sb.toString().trim();
    }

    @Override
    public String loginUser(UserLoginDto userLoginDto) {
        StringBuilder sb = new StringBuilder();

        Optional<User> user = this.userRepository
                .findByEmailAndPassword(userLoginDto.getEmail(), userLoginDto.getPassword());

        if(user.isPresent()) {
            if (this.loggedUser != null) {
                sb.append(String.format("User is already logged in%n"));
            } else {
                this.loggedUser = this.modelMapper.map(user.get(), UserDto.class);
                this.gameService.setLoggedUser(this.loggedUser);
                sb.append(String.format("Successfully logged in %s%n", user.get().getFullName()));
            }
        } else {
            sb.append(String.format("Incorrect username/password%n"));
        }

        return sb.toString().trim();
    }

    @Override
    public String logout() {
        if(this.loggedUser == null) {
            return "Cannot log out. No user was logged in%n";
        } else {
            String message = String.format("User %s successfully logged out%n", this.loggedUser.getFullName());
            this.loggedUser = null;
            return message;
        }
    }

}
