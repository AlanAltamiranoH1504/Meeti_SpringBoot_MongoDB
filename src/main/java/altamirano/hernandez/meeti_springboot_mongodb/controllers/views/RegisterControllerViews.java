package altamirano.hernandez.meeti_springboot_mongodb.controllers.views;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RegisterControllerViews {

    @GetMapping("/crear-cuenta")
    public String crearCuenta() {
        return "/usuarios/formRegistroUsuarios";
    }

    @GetMapping("/confirmar-cuenta/{token}")
    public String confirmarCuenta(Model model, @PathVariable String token) {
        model.addAttribute("token", token);
        return "/usuarios/confirmarCuenta";
    }

    @GetMapping("/iniciar-sesion")
    public String iniciarSesion() {
        return "/usuarios/formIniciarSesion";
    }
}
