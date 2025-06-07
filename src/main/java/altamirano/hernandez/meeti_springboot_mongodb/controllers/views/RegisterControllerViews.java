package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RegisterControllerViews {

    @GetMapping("/crear-cuenta")
    public String crearCuenta() {
        return "/usuarios/formRegistroUsuarios";
    }

    @GetMapping("/confirmar-cuenta/{token}")
    public String confirmarCuenta() {
        return "/usuarios/confirmarCuenta";
    }
}
