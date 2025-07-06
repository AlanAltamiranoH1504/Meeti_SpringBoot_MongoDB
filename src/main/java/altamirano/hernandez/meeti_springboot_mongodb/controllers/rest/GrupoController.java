package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.Error;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.UsuarioDTO;
import altamirano.hernandez.meeti_springboot_mongodb.services.cloudinary.CloudinaryService;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import altamirano.hernandez.meeti_springboot_mongodb.services.usuarios.UsuarioAutenticadoHelper;
import altamirano.hernandez.meeti_springboot_mongodb.utils.TokensString;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.*;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
    @Autowired
    private IGrupoService iGrupoService;
    @Autowired
    private IUsuarioService iUsuarioService;
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;
    @Autowired
    private CloudinaryService cloudinaryService;

    @GetMapping("")
    public ResponseEntity<?> findAll() {
        Map<String, Object> json = new HashMap<>();
        try {
            Usuario usuario = usuarioAutenticadoHelper.usuarioAutenticado();
            List<Grupo> grupos = iGrupoService.findByUserId(usuario.getId());
            if (grupos.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(grupos);
            } else {
                json.put("error", "No existe grupos creados");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@Valid @RequestBody Grupo grupo, BindingResult bindingResult) {
        Map<String, Object> json = new HashMap<>();
        if (bindingResult.hasErrors()) {
            Map<String, Object> errores = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                errores.put(error.getField(), error.getDefaultMessage());
            });
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
        } else {
            try {
                System.out.println(grupo.toString());

                Usuario usuario = usuarioAutenticadoHelper.usuarioAutenticado();
                grupo.setUsuario(usuario);
                grupo.setCategoriaId(grupo.getCategoriaId());
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

    @PutMapping("/save-imagen")
    public String saveImagenDeGrupo(@RequestParam("imagen") MultipartFile imagen) {
        Map<String, Object> json = new HashMap<>();
        try {
            //Validaciones de archivo
            if (imagen.isEmpty()) {
                json.put("error", "Imagen no cargada de forma correcta");
                return null;
            }
            //Validacion de extensiones
            String extensionArchivo = imagen.getContentType();
            List<String> extensionesValidas = Arrays.asList("image/png", "image/jpeg", "image/jpg");
            if (!extensionesValidas.contains(extensionArchivo)) {
                json.put("error", "Extension de imagen no valida. Solo png, jpeg o jpg");
                return null;
            }

            //Subida de imagen a cluodinary
            Map result = cloudinaryService.uploadImagen(imagen);
            String urlImagen = result.get("url").toString();
        } catch (Exception e) {
            Error error = new Error("Error en subida de imagen", e.getMessage());
        }
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable String id) {
        Map<String, Object> json = new HashMap<>();
        try {
            Optional<Grupo> grupo = iGrupoService.findById(id);
            Usuario usuarioInSesion = usuarioAutenticadoHelper.usuarioAutenticado();
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
    public ResponseEntity<?> update(@Valid @RequestBody altamirano.hernandez.meeti_springboot_mongodb.models.dto.Grupo grupo, BindingResult bindingResult, @PathVariable String id) {
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
                    group.setCategoriaId(grupo.getCategoriaId());
                    System.out.println(group.toString());

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
            String carpetaDestino = Paths.get("static/uploads").toAbsolutePath().toString();
            Files.deleteIfExists(Paths.get(carpetaDestino + File.separator + grupoPorEliminar.get().getImagen()));
            iGrupoService.deleteById(id);
            json.put("msg", "Grupo eliminado exitosamente");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
