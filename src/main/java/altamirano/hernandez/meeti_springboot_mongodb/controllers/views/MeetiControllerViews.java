package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/meetis_views")
public class MeetiControllerViews {

    @GetMapping("/nuevo_meeti")
    public String formNuevoMeeti() {
        return "meetis/formNuevoMeeti";
    }
}
