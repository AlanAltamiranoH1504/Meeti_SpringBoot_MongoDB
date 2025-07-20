package altamirano.hernandez.meeti_springboot_mongodb.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Document(collection = "meetis")
public class Meeti {
    @Id
    private String id;
    @NotBlank(message = "El grupo es obligatorio")
    private String grupo_id;
    private String usuario_id;
    @NotBlank(message = "El titulo es obligatorio")
    @Size(min = 3, max = 50, message = "El titulo debe tener una longitud de entre 3 y 50 caracteres")
    private String titulo;
    private String invitado;
    @NotNull(message = "La fecha es obligatoria")
    private Date fecha;
    @NotNull(message = "La hora es obligatoria")
    private LocalTime hora;
    @Positive(message = "El cupo debe ser minimo de 1")
    private int cupo;
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
    @NotBlank(message = "La direccion es obligatoria")
    private String direccion;
    @NotBlank(message = "La ciudad es obligatoria")
    private String ciudad;
    @NotBlank(message = "El estado es obligatorio")
    private String estado;
    @NotBlank(message = "El pais es obligatorio")
    private String pais;
    @NotBlank(message = "La latitud es obligatoria")
    private String lat;
    @NotBlank(message = "La longitud es obligatoria")
    private String lng;

    public Meeti() {
    }

    public Meeti(String grupo_id, String usuario_id, String titulo, String invitado, Date fecha, LocalTime hora, int cupo, String descripcion, String direccion, String ciudad, String estado, String pais, String lat, String lng) {
        this.grupo_id = grupo_id;
        this.usuario_id = usuario_id;
        this.titulo = titulo;
        this.invitado = invitado;
        this.fecha = fecha;
        this.hora = hora;
        this.cupo = cupo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
        this.lat = lat;
        this.lng = lng;
    }

    public Meeti(String id, String grupo_id, String usuario_id, String titulo, String invitado, Date fecha, LocalTime hora, int cupo, String descripcion, String direccion, String ciudad, String estado, String pais, String lat, String lng) {
        this.id = id;
        this.grupo_id = grupo_id;
        this.usuario_id = usuario_id;
        this.titulo = titulo;
        this.invitado = invitado;
        this.fecha = fecha;
        this.hora = hora;
        this.cupo = cupo;
        this.descripcion = descripcion;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.estado = estado;
        this.pais = pais;
        this.lat = lat;
        this.lng = lng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGrupo_id() {
        return grupo_id;
    }

    public void setGrupo_id(String grupo_id) {
        this.grupo_id = grupo_id;
    }

    public String getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(String usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInvitado() {
        return invitado;
    }

    public void setInvitado(String invitado) {
        this.invitado = invitado;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Meeti meeti = (Meeti) o;
        return cupo == meeti.cupo && Objects.equals(id, meeti.id) && Objects.equals(grupo_id, meeti.grupo_id) && Objects.equals(usuario_id, meeti.usuario_id) && Objects.equals(titulo, meeti.titulo) && Objects.equals(invitado, meeti.invitado) && Objects.equals(fecha, meeti.fecha) && Objects.equals(hora, meeti.hora) && Objects.equals(descripcion, meeti.descripcion) && Objects.equals(direccion, meeti.direccion) && Objects.equals(ciudad, meeti.ciudad) && Objects.equals(estado, meeti.estado) && Objects.equals(pais, meeti.pais) && Objects.equals(lat, meeti.lat) && Objects.equals(lng, meeti.lng);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, grupo_id, usuario_id, titulo, invitado, fecha, hora, cupo, descripcion, direccion, ciudad, estado, pais, lat, lng);
    }

    @Override
    public String toString() {
        return "Meeti{" +
                "id='" + id + '\'' +
                ", grupo_id='" + grupo_id + '\'' +
                ", usuario_id='" + usuario_id + '\'' +
                ", titulo='" + titulo + '\'' +
                ", invitado='" + invitado + '\'' +
                ", fecha=" + fecha +
                ", hora=" + hora +
                ", cupo=" + cupo +
                ", descripcion='" + descripcion + '\'' +
                ", direccion='" + direccion + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", estado='" + estado + '\'' +
                ", pais='" + pais + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                '}';
    }
}
