package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.UsuarioDTO;
import altamirano.hernandez.meeti_springboot_mongodb.services.usuarios.UsuarioAutenticadoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;

    @GetMapping("/usuario-in-session")
    public ResponseEntity<?> usuarioInSession() {
        Map<String, Object> json = new HashMap<>();
        UsuarioDTO usuarioEnSesion = usuarioAutenticadoHelper.usuarioAutenticado();
        if (usuarioEnSesion == null) {
            json.put("error", "Usuario no autenticado.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioEnSesion);
    }
}
