package com.example.GameShopApplication.services;
import com.example.GameShopApplication.domain.dto.AddGameDto;
import com.example.GameShopApplication.domain.dto.UserDto;
import com.example.GameShopApplication.domain.entities.Game;
import com.example.GameShopApplication.domain.entities.Role;
import com.example.GameShopApplication.repositories.GameRepository;
import com.example.GameShopApplication.utils.ValidatorUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class GameServiceImpl implements GameService {

    private final ModelMapper modelMapper;
    private final GameRepository gameRepository;
    private final ValidatorUtil validatorUtil;
    private UserDto loggedUser;

    @Autowired
    public GameServiceImpl(ModelMapper modelMapper, GameRepository gameRepository, ValidatorUtil validatorUtil) {
        this.modelMapper = modelMapper;
        this.gameRepository = gameRepository;
        this.validatorUtil = validatorUtil;
    }

    @Override
    public String addGame(AddGameDto addGameDto) {
        StringBuilder sb = new StringBuilder();

        if(!this.checkIfUserIsAdmin(this.loggedUser)) {
            sb.append("No logged admin user.");
        } else if (this.validatorUtil.isValid(addGameDto)) {
            Game game = this.modelMapper.map(addGameDto, Game.class);
            this.gameRepository.saveAndFlush(game);
            sb.append(String.format("Added %s%n", game.getTitle()));
        } else {
            this.validatorUtil.violations(addGameDto)
                    .forEach(error -> sb.append(String.format("%s%n", error.getMessage())));
        }

        return sb.toString().trim();
    }

    @Override
    public void setLoggedUser(UserDto userDto) {
        this.loggedUser = userDto;
    }

    @Override
    public boolean checkIfUserIsAdmin(UserDto userDto) {
        if (userDto == null || userDto.getRole().equals(Role.USER)) {
            return false;
        }
        return true;
    }

    @Override
    public String deleteGame(Long id) {
        StringBuilder sb = new StringBuilder();

        if (!this.checkIfUserIsAdmin(this.loggedUser)) {
            sb.append("No logged admin user.");
        } else {
            Optional<Game> game = this.gameRepository.findById(id);

            if (game.isPresent()) {
                sb.append(String.format("Deleted %s%n", game.get().getTitle()));
                this.gameRepository.delete(game.get());
            } else {
                sb.append(String.format("Game with id %d does not exist.%n", id));
            }
        }

        return sb.toString().trim();
    }


}
