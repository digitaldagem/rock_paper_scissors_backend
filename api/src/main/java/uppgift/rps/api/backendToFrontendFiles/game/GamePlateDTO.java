package uppgift.rps.api.backendToFrontendFiles.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class GamePlateDTO {

    String token;
    String playerOneName;
    String playerTwoName;
    String playerOneMove;
    String playerTwoMove;
    String status;
    String result;

    @JsonCreator
    public GamePlateDTO(
            @JsonProperty("token") String token,
            @JsonProperty("playerOneName") String playerOneName,
            @JsonProperty("playerTwoName") String playerTwoName,
            @JsonProperty("playerOneMove") String playerOneMove,
            @JsonProperty("playerTwoMove") String playerTwoMove,
            @JsonProperty("status") String status,
            @JsonProperty("result") String result
    ) {
        this.token = token;
        this.playerOneName = playerOneName;
        this.playerTwoName = playerTwoName;
        this.playerOneMove = playerOneMove;
        this.playerTwoMove = playerTwoMove;
        this.status = status;
        this.result = result;
    }

    public void setToken(String token) {
        this.token = token;
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

    public String getPlayerOneMove() {
        return playerOneMove;
    }

    public String getPlayerTwoMove() {
        return playerTwoMove;
    }

    public String getStatus() { return status; }

    public String getResult() { return result; }

}
