package altamirano.hernandez.meeti_springboot_mongodb.models;

import altamirano.hernandez.meeti_springboot_mongodb.models.dto.UsuarioDTO;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.w3c.dom.Text;

import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "grupos")
public class Grupo {
    @Id
    private String id;

    @NotBlank(message = "El nombre es obligatorio")
    @Indexed(unique = true)
    private String nombre;
    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 10, max = 500, message = "La longitud de la descripcion es de 10 a 500 caracteres")
    private String descripcion;

    //Columnas no obligatorias
    private String imagen;
    private String sitioWeb;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Un grupo tiene una categoria
//    @NotBlank(message = "La categoria es obligatoria")
    String categoriaId;
    //Un usuario tiene un Usuario
    Usuario usuario;

    //Constructores
    public Grupo() {
    }
    public Grupo(String nombre, String descripcion, String imagen, String sitioWeb, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sitioWeb = sitioWeb;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
    public Grupo(String nombre, String descripcion, String imagen, String sitioWeb, String categoriaId, LocalDateTime createdAt, LocalDateTime updatedAt, Usuario usuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sitioWeb = sitioWeb;
        this.categoriaId = categoriaId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.usuario = usuario;
    }
    public Grupo(String id, String nombre, String descripcion, String imagen, String sitioWeb, String categoriaId, LocalDateTime createdAt, LocalDateTime updatedAt, Usuario usuario) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sitioWeb = sitioWeb;
        this.categoriaId = categoriaId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.usuario = usuario;
    }

    // G y S
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public void setSitioWeb(String sitioWeb) {
        this.sitioWeb = sitioWeb;
    }

    public String getCategoriaId() {
        return categoriaId;
    }

    public void setCategoriaId(String categoriaId) {
        this.categoriaId = categoriaId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    // E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Grupo grupo = (Grupo) o;
        return Objects.equals(id, grupo.id) && Objects.equals(nombre, grupo.nombre) && Objects.equals(descripcion, grupo.descripcion) && Objects.equals(imagen, grupo.imagen) && Objects.equals(sitioWeb, grupo.sitioWeb) && Objects.equals(createdAt, grupo.createdAt) && Objects.equals(updatedAt, grupo.updatedAt) && Objects.equals(categoriaId, grupo.categoriaId) && Objects.equals(usuario, grupo.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, descripcion, imagen, sitioWeb, createdAt, updatedAt, categoriaId, usuario);
    }

    @Override
    public String toString() {
        return "Grupo{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", imagen='" + imagen + '\'' +
                ", sitioWeb='" + sitioWeb + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", categoriaId='" + categoriaId + '\'' +
                ", usuario=" + usuario +
                '}';
    }
}
