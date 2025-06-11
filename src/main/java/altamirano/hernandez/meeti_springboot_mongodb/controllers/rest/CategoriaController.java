package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Categoria;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.ICategoriaService;
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
@RequestMapping("/categorias")
public class CategoriaController {
    @Autowired
    private ICategoriaService iCategoriaService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Categoria> categorias = iCategoriaService.getCategorias();
            if (categorias.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(categorias);
            } else {
                json.put("msg", "No existen categorias");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Categoria categoria, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                categoria.setCreatedAt(LocalDateTime.now());
                Categoria categoriaSaved = iCategoriaService.save(categoria);
                json.put("msg", "Categoria guardada con exito");

                return ResponseEntity.status(HttpStatus.CREATED).body(json);
            } catch (RuntimeException e) {
                json.put("msg", e.getMessage());
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
            }
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Categoria> categoria = iCategoriaService.findById(id);
            if (categoria.isPresent()) {
                Categoria categoriaFound = categoria.get();
                return ResponseEntity.status(HttpStatus.OK).body(categoriaFound);
            } else {
                json.put("msg", "Categoria no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updated(@Valid @RequestBody Categoria categoria, @PathVariable String id, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errors = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } else {
            try {
                Optional<Categoria> categoriaFound = iCategoriaService.findById(id);
                if (categoriaFound.isPresent()) {
                    categoria.setCreatedAt(categoriaFound.get().getCreatedAt());
                    categoria.setUpdatedAt(LocalDateTime.now());
                    iCategoriaService.save(categoria);

                    json.put("msg", "Categoria actualizada con exito");
                    return ResponseEntity.status(HttpStatus.OK).body(json);
                } else {
                    json.put("msg", "Categoria no encontrada");
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
            Optional<Categoria> categoria = iCategoriaService.findById(id);
            if (categoria.isPresent()) {
                iCategoriaService.deleteById(id);
                json.put("msg", "Categoria eliminada con exito");
                return ResponseEntity.status(HttpStatus.OK).body(json);
            } else {
                json.put("msg", "Categoria no encontrada");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}
