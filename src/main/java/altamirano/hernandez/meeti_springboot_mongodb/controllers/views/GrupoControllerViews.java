package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/grupos-views")
public class GrupoControllerViews {

    @GetMapping("/nuevo-grupo")
    public String formNuevoGrupo() {
        return "grupos/formNuevoGrupo";
    }
}
