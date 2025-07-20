package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Meeti;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.Error;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IMeetiService;
import altamirano.hernandez.meeti_springboot_mongodb.services.usuarios.UsuarioAutenticadoHelper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/meetis")
public class MeetiController {

    @Autowired
    private IMeetiService iMeetiService;
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;

    @GetMapping("/findAll")
    public ResponseEntity<?> findAll() {
        try{
            List<Meeti> meetis = iMeetiService.findAll();
            if (meetis.isEmpty()) {
                Error error = new Error("Meetis no encotrado", "El numero de meetis registrados es cero");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
            return ResponseEntity.status(HttpStatus.OK).body(meetis);
        } catch (RuntimeException e) {
            Error error =new Error("Error en listado de meetis", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/findAllByUserId")
    public ResponseEntity<?> findAllByUserId() {
        try {
            Usuario userInSession = usuarioAutenticadoHelper.usuarioAutenticado();
            List<Meeti> meetisByUserId = iMeetiService.findAllByUserId(userInSession.getId());
            if (meetisByUserId.isEmpty()) {
                Error error = new Error("Meetis no encontrados", "El usuario no tiene meetis creados");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(error);
            }
            return ResponseEntity.status(HttpStatus.OK).body(meetisByUserId);
        } catch (RuntimeException e) {
            Error error =new Error("Error en listado de meetis del usuario", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        try {
            Optional<Meeti> meetiToShow = iMeetiService.findById(id);
            if (meetiToShow.isPresent()) {
                Usuario usuario = usuarioAutenticadoHelper.usuarioAutenticado();
                if (meetiToShow.get().getUsuario_id().equals(usuario.getId())) {
                    return ResponseEntity.status(HttpStatus.OK).body(meetiToShow.get());
                } else {
                    Error error = new Error("Recurso no permitido", "El meeti no se encuentra disponble para este usuario");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
                }
            }
            Error error = new Error("Meeti no encontrado", "El meeti con el id " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (RuntimeException e) {
            Error error =new Error("Error en busquea de meeti con id: " + id, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Meeti meeti, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        try {
            if (bindingResult.hasErrors()) {
                Map<String, Object> errores = new HashMap<>();
                bindingResult.getFieldErrors().forEach(error -> {
                    errores.put(error.getField(), error.getDefaultMessage());
                });
                return ResponseEntity.status(HttpStatus.CONFLICT).body(errores);
            } else {
                Usuario userInSession = usuarioAutenticadoHelper.usuarioAutenticado();
                meeti.setUsuario_id(userInSession.getId());
                iMeetiService.save(meeti);
                json.put("sucess", "Meeti creado correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            }
        } catch (RuntimeException e) {
            Error error = new Error("Error en creacion de meeti nuevo", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletById(@PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        try{
            Optional<Meeti> meetiToDelete = iMeetiService.findById(id);
            if (meetiToDelete.isPresent()) {
                Usuario userInSession = usuarioAutenticadoHelper.usuarioAutenticado();
                if (userInSession.getId().equals(meetiToDelete.get().getUsuario_id())) {
                    iMeetiService.deleteById(id);
                    json.put("sucess", "Meeti eliminado correctamente");
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                } else{
                    Error error = new Error("Recurso no permitido", "El meeti no se encuentra disponble para este usuario");
                    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
                }
            }
            Error error = new Error("Error en busqueda de meeti con id: " + id, "El meeti con el id " + id + " no existe");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        } catch (RuntimeException e) {
            Error error = new Error("Error en eliminacion de meeti", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
        }
    }
}
