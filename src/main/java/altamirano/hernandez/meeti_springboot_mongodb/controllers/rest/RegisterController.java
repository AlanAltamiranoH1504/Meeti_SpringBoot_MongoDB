package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.Token;
import altamirano.hernandez.meeti_springboot_mongodb.services.emails.EnvioEmails;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import altamirano.hernandez.meeti_springboot_mongodb.utils.TokensString;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private EnvioEmails emails;

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
                Optional<Usuario> usuarioUserName = iUsuarioService.findByUsername(usuario.getUsername());
                if (usuarioUserName.isPresent()) {
                    json.put("msg", "Username ya registrado.");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
                }

                Optional<Usuario> usarioEmail = iUsuarioService.findByEmail(usuario.getEmail());
                if (usarioEmail.isPresent()) {
                    json.put("msg", "Email ya registrado.");
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
                }

                usuario.setToken(TokensString.tokenString());
                iUsuarioService.save(usuario);
                String nombreCompleto = usuario.getNombre() + " " + usuario.getApellidos();
                emails.emailConfirmacionCuenta(usuario.getEmail(), "Confirmacion de Cuenta", nombreCompleto, usuario.getToken());
                json.put("msg", "Usuario creado correctamente!");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
            }
        }
    }

    @PostMapping("/confirmar-cuenta/{token}")
    public ResponseEntity<?> confirmarCuenta(@PathVariable String token) {
        Map<String, Object> json = new HashMap<>();
        Optional<Usuario> usuarioConToken = iUsuarioService.findByToken(token);
        if (!usuarioConToken.isPresent()) {
            json.put("msg", "Usuario no encontrado.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
        } else {
            usuarioConToken.get().setToken("");
            usuarioConToken.get().setConfirmado(true);
            iUsuarioService.save(usuarioConToken.get());
            String nombreCompleto = usuarioConToken.get().getNombre() + " " + usuarioConToken.get().getApellidos();

            emails.emailCuentaConfirmada(usuarioConToken.get().getEmail(), "Cuenta Confirmada Correctamente", nombreCompleto);
            json.put("msg", "Usuario confirmado correctamente!");

            return ResponseEntity.status(200).body(json);
        }
    }
}
