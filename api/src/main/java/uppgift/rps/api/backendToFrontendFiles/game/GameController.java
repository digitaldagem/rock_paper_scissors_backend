package uppgift.rps.api.backendToFrontendFiles.game;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uppgift.rps.api.serviceFiles.GameService;
import uppgift.rps.api.storageFiles.game.Game;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<GamePlateDTO> getListOfGames() {
        return gameService.getListOfGames().stream()
                .map(game -> game.getGamePlate(game.getToken())).collect(Collectors.toList());
    }

    @GetMapping(value = "/game/{token}")
    @PreAuthorize("hasRole('USER')")
    public GamePlateDTO getGame(@PathVariable("token") String token) {
        Game game = gameService.getGame(token);
        return game.getGamePlate(token);
    }

    @GetMapping(value = "/game/start/{name}")
    @PreAuthorize("hasRole('USER')")
    public GamePlateDTO startGame(@PathVariable("name") String name) {
        Game game = gameService.startGame(name);
        return game.getGamePlate(game.getToken());
    }

    @GetMapping(value = "/game/{token}/join/{name}")
    @PreAuthorize("hasRole('USER')")
    public GamePlateDTO joinGame(@PathVariable("token") String token, @PathVariable("name") String name) {
        Game game = gameService.joinGame(token, name);
        return game.getGamePlate(token);
    }

    @GetMapping(value = "/game/{token}/{name}/{sign}")
    @PreAuthorize("hasRole('USER')")
    public GamePlateDTO setPlayerMove(@PathVariable("token") String token,
                                      @PathVariable("name") String name, @PathVariable("sign") String sign) {
        Game game = gameService.setPlayerMove(token, name, sign);
        return game.getGamePlate(token);
    }

    @DeleteMapping(value = "/game/{token}")
    @PreAuthorize("hasRole('USER')")
    public void deleteGame(@PathVariable("token") String token) {
        gameService.deleteGame(token);
    }

}
