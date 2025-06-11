package altamirano.hernandez.meeti_springboot_mongodb.controllers.rest;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/administracion")
public class AdminController {
    @Autowired
    private IGrupoService iGrupoService;

    @GetMapping("/list-grupos")
    public ResponseEntity<?> listGrupos(@CookieValue("usuario_id") String userId) {
        Map<String, Object> json = new HashMap<>();
        try {
            List<Grupo> grupos = iGrupoService.findByUserId(userId);
            if (grupos.size() > 0) {
                return ResponseEntity.status(HttpStatus.OK).body(grupos);
            } else {
                json.put("msg", "No hay grupos existentes");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(json);
            }
        } catch (RuntimeException e) {
            json.put("msg", e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(json);
        }
    }
}
