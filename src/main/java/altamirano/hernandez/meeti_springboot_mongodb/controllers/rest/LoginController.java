package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.jwt.JwtService;
import altamirano.hernandez.meeti_springboot_mongodb.models.JwtResponse;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.LoginRequest;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtService jwtService;

    @PostMapping("")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            Optional<Usuario> usuario = iUsuarioService.findByEmail(loginRequest.getEmail());
            if (!usuario.isPresent()) {
                json.put("msg", "Usuario no registrado con el email " + loginRequest.getEmail());
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
            } else {
                if (passwordEncoder.matches(loginRequest.getPassword(), usuario.get().getPassword())) {
                    Usuario usuarioActual = usuario.get();
                    String token = jwtService.generateToken(usuarioActual.getEmail());
                    JwtResponse jwtResponse = new JwtResponse(token, usuarioActual.getNombre());
                    return ResponseEntity.status(HttpStatus.OK).body(jwtResponse);
                } else {
                    json.put("msg", "Error en credenciales del usuario");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
                }
            }
        }

    }
}
