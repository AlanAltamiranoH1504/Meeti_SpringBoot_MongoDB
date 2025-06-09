package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//@RequestMapping("/home")
public class HomeControllerViews {

    @GetMapping("/")
    public String Home(){
        return "home/home";
    }
}
