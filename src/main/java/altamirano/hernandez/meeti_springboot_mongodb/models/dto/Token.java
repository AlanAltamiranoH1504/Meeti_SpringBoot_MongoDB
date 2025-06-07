package altamirano.hernandez.meeti_springboot_mongodb.models.dto;

import jakarta.validation.constraints.NotBlank;

public class Token {
    @NotBlank(message = "El token es obligatorio")
    private String token;

    public Token(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }

}
