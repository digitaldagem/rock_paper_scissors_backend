package uppgift.rps.api.storageFiles.token;

import java.util.UUID;

public class Token {

    private String token = UUID.randomUUID().toString();

    public String getToken() {
        return token;
    }

}
