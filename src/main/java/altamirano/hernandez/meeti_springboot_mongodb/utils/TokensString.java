package altamirano.hernandez.meeti_springboot_mongodb.utils;

import java.util.UUID;

public class TokensString {

    public static String tokenString () {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
