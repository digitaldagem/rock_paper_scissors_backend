package uppgift.rps.api.storageFiles.game;

import uppgift.rps.api.backendToFrontendFiles.game.GamePlateDTO;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Game {

    @Id
    private String token;
    private String playerOneName;
    private String playerTwoName;
    private String playerOneMove;
    private String playerTwoMove;

    public Game() {
    }

    public Game(String token, String playerOneName) {
        this.token = token;
        this.playerOneName = playerOneName;
    }

    public GamePlateDTO getGamePlate(String token) {
        return new GamePlateDTO(
                this.token.equals(token) ? token : "Game Not found",
                playerOneName,
                playerTwoName != null ? getPlayerTwoName() : "",
                playerOneMove != null ? getPlayerOneMove() : "",
                playerTwoMove != null ? getPlayerTwoMove() : "",
                playerTwoName != null ? "active" : "open",
                (playerOneMove != null && playerTwoMove != null) ? calculateWinner(playerOneMove, playerTwoMove) : ""
        );
    }

    public String getToken() {
        return token;
    }

    public String getPlayerOneName() {
        return playerOneName;
    }

    public String getPlayerTwoName() {
        return playerTwoName;
    }

    public void setPlayerTwoName(String playerTwoName) {
        this.playerTwoName = playerTwoName;
    }

    public String getPlayerOneMove() {
        return playerOneMove;
    }

    public Game setPlayerOneMove(String playerOneMove) {
        this.playerOneMove = playerOneMove;
        return this;
    }

    public String getPlayerTwoMove() {
        return playerTwoMove;
    }

    public Game setPlayerTwoMove(String playerTwoMove) {
        this.playerTwoMove = playerTwoMove;
        return this;
    }

    public String calculateWinner(String playerOneMove, String playerTwoMove) {
        if (playerOneMove.equals(playerTwoMove)) {
            return "Its a draw";
        }
        if ((playerOneMove.equals("rock") && playerTwoMove.equals("scissors")) ||
                (playerOneMove.equals("scissors") && playerTwoMove.equals("paper")) ||
                (playerOneMove.equals("paper") && playerTwoMove.equals("rock"))) {
            return playerOneName + " wins";
        }
        return playerTwoName + " wins";
    }
}
