package altamirano.hernandez.meeti_springboot_mongodb.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document(collection = "roles")
public class Rol {
    @Id
    private String id;
    private String nombre;

    public Rol() {

    }
    public Rol(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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

    //E y H
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Rol rol = (Rol) o;
        return Objects.equals(id, rol.id) && Objects.equals(nombre, rol.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre);
    }
}
