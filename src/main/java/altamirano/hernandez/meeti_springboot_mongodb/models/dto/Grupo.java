package altamirano.hernandez.meeti_springboot_mongodb.models.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;

public class Grupo {
    @NotBlank(message = "El nombre es obligatorio")
    @Indexed(unique = true)
    private String nombre;

    @NotBlank(message = "La descripcion es obligatoria")
    @Size(min = 10, max = 500, message = "La longitud de la descripcion es de 10 a 500 caracteres")
    private String descripcion;
    private String imagen;
    private String sitioWeb;
    @NotBlank(message = "La categoria es obligatoria")
    String categoria;

    public Grupo() {
    }
    public Grupo(String nombre, String descripcion, String imagen, String sitioWeb, String categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.sitioWeb = sitioWeb;
        this.categoria = categoria;
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
}
