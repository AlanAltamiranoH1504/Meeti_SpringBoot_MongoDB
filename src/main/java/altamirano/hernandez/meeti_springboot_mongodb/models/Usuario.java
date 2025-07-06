package altamirano.hernandez.meeti_springboot_mongodb.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Document(collection = "usuarios")
public class Usuario {
    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    @Size(min = 3, max = 70, message = "El nombre debe tener un largo de entre 3 y 70 caracteres")
    private String nombre;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Size(min = 5, message = "Los apallidos deben tener un minimo de 5 caracteres")
    private String apellidos;

    @NotBlank(message = "El username es obligatorio")
    @Size(min = 5, message = "El username debe tener un minimo de 5 caracteres")
    private String username;

    @NotBlank(message = "El email es obligatorio")
    @Size(min = 3, max = 50, message = "El email debe tener una extension de entre 3 y 50 caracteres")
    private String email;

    @NotBlank(message = "El password es obligatorio")
    @Size(min = 5, max = 70, message = "El password debe tener una extension de entre 5 70 caracteres")
    private String password;

    //Atributos no aplicables para validacion
    private String token;
    private boolean confirmado;

    //Muchos roles para un usuario
    List<Rol> roles = new ArrayList<>();

    //Constructores
    public Usuario() {
    }
    public Usuario(String nombre, String apellidos, String username, String email) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.email = email;
    }

    public Usuario(String nombre, String apellidos, String username, String email, String password) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public Usuario(String nombre, String apellidos, String username, String email, String password, String token, boolean confirmado, List<Rol> roles) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.username = username;
        this.email = email;
        this.password = password;
        this.token = token;
        this.confirmado = confirmado;
        this.roles = roles;
    }

    //G y S
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isConfirmado() {
        return confirmado;
    }

    public void setConfirmado(boolean confirmado) {
        this.confirmado = confirmado;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    //E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return confirmado == usuario.confirmado && Objects.equals(id, usuario.id) && Objects.equals(nombre, usuario.nombre) && Objects.equals(apellidos, usuario.apellidos) && Objects.equals(username, usuario.username) && Objects.equals(email, usuario.email) && Objects.equals(password, usuario.password) && Objects.equals(token, usuario.token) && Objects.equals(roles, usuario.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellidos, username, email, password, token, confirmado, roles);
    }
}
