package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import altamirano.hernandez.meeti_springboot_mongodb.utils.TokensString;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping("/crear-cuenta")
    public ResponseEntity<?> crearCuenta(@Valid @RequestBody Usuario usuario, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Optional <Usuario> usuarioUserName = iUsuarioService.findByUsername(usuario.getUsername());
                if (usuarioUserName.isPresent()) {
                    json.put("msg", "Username ya registrado.");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
                }

                Optional<Usuario> usarioEmail = iUsuarioService.findByEmail(usuario.getEmail());
                if (usarioEmail.isPresent()){
                    json.put("msg", "Email ya registrado.");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
                }

                usuario.setToken(TokensString.tokenString());
                iUsuarioService.save(usuario);
                json.put("msg", "Usuario creado correctamente!");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }
}
