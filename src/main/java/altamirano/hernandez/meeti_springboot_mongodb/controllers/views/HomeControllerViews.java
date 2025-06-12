package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Controller
public class HomeControllerViews {
    @Autowired
    private IGrupoService iGrupoService;

    @GetMapping("/")
    public String Home(){
        return "administracion/home";
    }

    @GetMapping("/grupo-edicion/{id}")
    public String grupoEdicion(@PathVariable String id, @CookieValue("usuario_id") String idUsuario) {
        Optional<Grupo> grupo = iGrupoService.findById(id);
        if (grupo.isPresent()) {
            if (idUsuario.equals(grupo.get().getUsuario().getId())) {
                return "administracion/grupoEdicion";
            } else {
                return "error/403";
            }
        } else {
            return "error/404";
        }
    }
}
