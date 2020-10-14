package uppgift.rps.api.serviceFiles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import uppgift.rps.api.storageFiles.game.Game;
import uppgift.rps.api.storageFiles.game.GameRepository;
import uppgift.rps.api.storageFiles.token.Token;

import java.util.List;
import java.util.Optional;

@Component
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    public List<Game> getListOfGames() {
        return gameRepository.findAll();
    }

    public Game getGame(String token) {
        return gameRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Not found " + token));
    }

    public Game startGame(String playerOneName) {
        Token token = new Token();
        String gameToken = token.getToken();
        Game game = new Game(gameToken, playerOneName);
        return gameRepository.save(game);
    }

    public Game joinGame(String token, String playerTwoName) {
        Optional<Game> optionalGame = Optional.ofNullable(gameRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Not found " + token)));
        if (optionalGame.get().getPlayerTwoName() != null) {
            return null;
        } else {
            optionalGame.get().setPlayerTwoName(playerTwoName);
            return gameRepository.save(optionalGame.get());
        }

    }

    public Game setPlayerMove(String token, String name, String sign) {
        Optional<Game> optionalGame = Optional.ofNullable(gameRepository.findById(token)
                .orElseThrow(() -> new RuntimeException("Not found " + token)));
        if (optionalGame.get().getPlayerOneName().equals(name)) {
            optionalGame.get().setPlayerOneMove(sign);
        }
        if (optionalGame.get().getPlayerTwoName().equals(name)) {
            optionalGame.get().setPlayerTwoMove(sign);
        }
        return optionalGame.get();
    }

    public void deleteGame(String token) {
        gameRepository.deleteById(token);
    }

}
