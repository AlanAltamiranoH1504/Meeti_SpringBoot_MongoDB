package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService iGrupoService;
    @Autowired
    private IUsuarioService iUsuarioService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Grupo> grupos = iGrupoService.findAll();
            if (grupos.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(grupos);
            } else {
                json.put("msg", "No existe grupos creados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Grupo grupo, @CookieValue("usuario_id") String usuarioID, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Usuario usuario = iUsuarioService.findById(usuarioID);
                grupo.setUsuario(usuario);
                grupo.setCreatedAt(LocalDateTime.now());
                Grupo grupoSaved = iGrupoService.save(grupo);
                json.put("msg", "Grupo guardado exitosamente");

                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                json.put("msg", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id, @CookieValue("usuario_id") String usuarioId) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Grupo> grupo = iGrupoService.findById(id);
            if (grupo.isPresent()) {
                return ResponseEntity.status(HttpStatus.OK).body(grupo.get());
            } else {
                json.put("msg", "Grupo no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody altamirano.hernandez.meeti_springboot_mongodb.models.dto.Grupo grupo, @CookieValue("usuario_id") String usuarioID, BindingResult bindingResult, @PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                Optional<Grupo> grupoPorActualizar = iGrupoService.findById(id);
                if (grupoPorActualizar.isPresent()) {
                    var group = grupoPorActualizar.get();
                    group.setNombre(grupo.getNombre());
                    group.setDescripcion(grupo.getDescripcion());
                    group.setImagen(grupo.getImagen());
                    group.setSitioWeb(grupo.getSitioWeb());
                    group.setCategoria(grupo.getCategoria());

                    Grupo savedGrupo = iGrupoService.save(group);
                    json.put("msg", "Grupo actualizado exitosamente");
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                } else {
                    json.put("msg", "Grupo no encontrado");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
                }
            } catch (RuntimeException e) {
                json.put("msg", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Grupo> grupoPorEliminar = iGrupoService.findById(id);
            if (!grupoPorEliminar.isPresent()) {
                json.put("msg", "Grupo no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
            iGrupoService.deleteById(id);
            json.put("msg", "Grupo eliminado exitosamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
