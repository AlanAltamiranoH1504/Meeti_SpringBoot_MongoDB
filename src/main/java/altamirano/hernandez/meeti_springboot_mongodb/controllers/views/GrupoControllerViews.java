package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/grupos-views")
public class GrupoControllerViews {

    @Autowired
    private IGrupoService iGrupoService;

    @GetMapping("/nuevo-grupo")
    public String formNuevoGrupo() {
        return "grupos/formNuevoGrupo";
    }

    @GetMapping("/grupo-imagen/{id}")
    public String formGrupoImagen(@PathVariable String id, @CookieValue("usuario_id") String idUsuario) {
        Optional<Grupo> grupo = iGrupoService.findById(id);
        if (!grupo.isPresent()) {
            return "error/404";
        } else {
            if (!grupo.get().getUsuario().getId().equals(idUsuario)) {
                return "error/403";
            } else {
                return "grupos/formGrupoImagen";
            }
        }
    }
}
