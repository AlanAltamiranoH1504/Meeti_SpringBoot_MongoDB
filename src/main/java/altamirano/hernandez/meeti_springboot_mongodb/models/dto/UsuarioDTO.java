package altamirano.hernandez.meeti_springboot_mongodb.models.dto;

public class UsuarioDTO {
    private String id;
    private String nombre;
    private String username;
    private String apellidos;
    private String password;
    private String email;

    public UsuarioDTO(String id, String nombre, String username, String apellidos, String email, String password) {
        this.id = id;
        this.nombre = nombre;
        this.username = username;
        this.apellidos = apellidos;
        this.email = email;
        this.password = password;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
