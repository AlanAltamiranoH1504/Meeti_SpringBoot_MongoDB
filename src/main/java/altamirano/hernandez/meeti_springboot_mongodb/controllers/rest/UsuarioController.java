package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.Error;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.UsuarioDTO;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import altamirano.hernandez.meeti_springboot_mongodb.services.usuarios.UsuarioAutenticadoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("/usuario-in-session")
    public ResponseEntity<?> usuarioInSession() {
        Map<String, Object> json = new HashMap<>();
        Usuario usuarioEnSesion = usuarioAutenticadoHelper.usuarioAutenticado();
        if (usuarioEnSesion == null) {
            json.put("error", "Usuario no autenticado.");
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usuarioEnSesion);
    }

    @PutMapping("/update-perfiil")
    public ResponseEntity<?> updatePerfil(@RequestBody UsuarioDTO usuarioDTO, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
        } else {
            try {
                Usuario usuario = usuarioAutenticadoHelper.usuarioAutenticado();
                //Seteo de nuevos valores
                usuario.setNombre(usuarioDTO.getNombre());
                usuario.setApellidos(usuarioDTO.getApellidos());
                usuario.setUsername(usuarioDTO.getUsername());
                usuario.setEmail(usuarioDTO.getEmail());
                usuario.setPassword(passwordEncoder.encode(usuarioDTO.getPassword()));
                iUsuarioService.save(usuario);

                json.put("success", "Usuario actualizado exitosamente.");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } catch (RuntimeException e) {
                Error error = new Error("Error en actualizacion de perfil", e.getMessage());
                json.put("error", error);
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }
}
