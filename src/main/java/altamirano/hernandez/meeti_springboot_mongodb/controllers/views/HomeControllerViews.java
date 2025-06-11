package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeControllerViews {

    @GetMapping("/")
    public String Home(){
        return "administracion/home";
    }
}
