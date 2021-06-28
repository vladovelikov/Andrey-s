package com.example.GameShopApplication.controller;
import com.example.GameShopApplication.domain.dto.AddGameDto;
import com.example.GameShopApplication.domain.dto.UserLoginDto;
import com.example.GameShopApplication.domain.dto.UserRegisterDto;
import com.example.GameShopApplication.services.GameService;
import com.example.GameShopApplication.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class AppController implements CommandLineRunner {

    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public AppController(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    @Override
    public void run(String... args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            String[] tokens = reader.readLine().split("\\|");

            switch (tokens[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(tokens[1], tokens[2], tokens[3], tokens[4]);
                    System.out.println(this.userService.registerUser(userRegisterDto));
                    break;
                case "LoginUser":
                    UserLoginDto userLoginDto = new UserLoginDto(tokens[1], tokens[2]);
                    System.out.println(this.userService.loginUser(userLoginDto));
                    break;
                case "Logout":
                    System.out.println(this.userService.logout());
                    break;
                case "AddGame":
                    String date = tokens[7];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MM-yyyy");
                    LocalDate localDate = LocalDate.parse(date, formatter);
                    AddGameDto gameDto = new AddGameDto(tokens[1], new BigDecimal(tokens[2]), Double.parseDouble(tokens[3]),
                            tokens[4], tokens[5], tokens[6], localDate);
                    System.out.println(this.gameService.addGame(gameDto));
                    break;
                case "DeleteGame":
                    Long id = Long.parseLong(tokens[1]);
                    System.out.println(this.gameService.deleteGame(id));
                    break;
            }
        }


    }
}
