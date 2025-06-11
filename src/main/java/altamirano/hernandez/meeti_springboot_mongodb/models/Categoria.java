package altamirano.hernandez.meeti_springboot_mongodb.models;

import jakarta.validation.constraints.NotBlank;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Document(collection = "categorias")
public class Categoria {
    @Id
    private String id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    //Constructores
    public Categoria() {}
    private Categoria(String nombre) {
        this.nombre = nombre;
    }
    public Categoria(String id, String nombre, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.nombre = nombre;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    //E y H

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return Objects.equals(id, categoria.id) && Objects.equals(nombre, categoria.nombre) && Objects.equals(createdAt, categoria.createdAt) && Objects.equals(updatedAt, categoria.updatedAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, createdAt, updatedAt);
    }
}
