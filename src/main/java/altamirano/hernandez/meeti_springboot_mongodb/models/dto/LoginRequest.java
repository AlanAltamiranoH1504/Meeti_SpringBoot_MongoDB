package altamirano.hernandez.meeti_springboot_mongodb.models.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

public class LoginRequest {
    @NotBlank(message = "El email es obligatorio")
    private String email;
    @NotBlank(message = "El password es obligatorio")
    private String password;

    public LoginRequest() {

    }
    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        LoginRequest that = (LoginRequest) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
