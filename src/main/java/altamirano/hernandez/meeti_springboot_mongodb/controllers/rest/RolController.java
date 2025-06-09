package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IRolService;
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
@RequestMapping("/roles")
public class RolController {
    @Autowired
    private IRolService iRolService;

    @GetMapping("")
    public ResponseEntity<?> findAll(){
        Map<String, Object> json = new HashMap<>();
        try {
            List<Rol> roles = iRolService.findAll();
            return ResponseEntity.status(HttpStatus.OK).body(roles);
        } catch (RuntimeException e) {
            json.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id){
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Rol> rol =iRolService.findById(id);
            if (rol.isPresent()){
                return ResponseEntity.status(HttpStatus.OK).body(rol.get());
            } else {
                json.put("error", "Rol no existente");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Rol rol, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                iRolService.save(rol);
                json.put("msg", "El rol se ha guardado correctamente");
                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                json.put("error", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }
}
