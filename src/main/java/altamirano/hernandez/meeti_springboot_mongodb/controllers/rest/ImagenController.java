package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.services.cloudinary.CloudinaryService;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import altamirano.hernandez.meeti_springboot_mongodb.services.usuarios.UsuarioAutenticadoHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/imagenes")
public class ImagenController {
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private IGrupoService iGrupoService;
    @Autowired
    private UsuarioAutenticadoHelper usuarioAutenticadoHelper;
    @Autowired
    private IUsuarioService iUsuarioService;

    @PostMapping("/grupo/{id}")
    public ResponseEntity<?> updateImagenGrupo(@RequestParam MultipartFile imagen, @PathVariable String id){
        Map<String, Object> json = new HashMap<>();
        try {
            //Validacion de existencia de archivo
            if (imagen.isEmpty()) {
                json.put("error", "Imagen no subida correctamente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Validacion de extensiones
            String extensionArchivo = imagen.getContentType();
            List<String> extensionesValidas = Arrays.asList("image/jpeg", "image/png", "image/jpg");
            if (!extensionesValidas.contains(extensionArchivo)) {
                json.put("error", "Extension de archivo no valida");
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(json);
            }

            //Busqueda de grupo por id y verifcacion de dueño
            Optional<Grupo> grupoToUpdate = iGrupoService.findById(id);
            if (!grupoToUpdate.isPresent()) {
                json.put("error", "Grupo no encontrado");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
//            Usuario userInSesion = usuarioAutenticadoHelper.usuarioAutenticado();
//            if (!grupoToUpdate.get().getUsuario().getId().equals(userInSesion.getId())) {
//                json.put("error", "No tienes permiso para editar este grupo");
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(json);
//            }

            Grupo datosGrupo = grupoToUpdate.get();
            //Subida de imagen a cloudinary
            Map result = cloudinaryService.uploadImagen(imagen);
            String url = result.get("url").toString();
            datosGrupo.setImagen(url);
            iGrupoService.save(datosGrupo);
            json.put("success", "Imagen de grupo actualizada.");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/avatar")
    public ResponseEntity<?> updateImagenAvatar(@RequestParam MultipartFile imagen){
        Map<String, Object> json = new HashMap<>();
        try {
            //Validacion de archivi
            if (imagen.isEmpty()) {
                json.put("error", "Imagen no subida correctamente");
                return ResponseEntity.status(HttpStatus.CONFLICT).body(json);
            }

            //Validacion de extensiones
            String extensionArchivo = imagen.getContentType();
            List<String> extensionesValidas = Arrays.asList("image/jpeg", "image/png", "image/jpg");
            if (!extensionesValidas.contains(extensionArchivo)) {
                json.put("error", "Extension de archivo no valida");
                return ResponseEntity.status(HttpStatus.UNSUPPORTED_MEDIA_TYPE).body(json);
            }

            //Subida de archivo a cloud
            Map result = cloudinaryService.uploadImagen(imagen);
            String url = result.get("url").toString();
            Usuario usuarioInSession = usuarioAutenticadoHelper.usuarioAutenticado();
            usuarioInSession.setImagen(url);
            iUsuarioService.save(usuarioInSession);
            json.put("success", "Imagen de perfil actualizada.");
            return ResponseEntity.status(HttpStatus.OK).body(json);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
