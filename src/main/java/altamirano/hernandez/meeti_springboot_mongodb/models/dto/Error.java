package altamirano.hernandez.meeti_springboot_mongodb.models.dto;

public class Error {
    private String error;
    private String mensaje;

    public Error(String error, String mensaje) {
        this.error = error;
        this.mensaje = mensaje;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
