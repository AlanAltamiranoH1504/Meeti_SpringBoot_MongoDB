package altamirano.hernandez.meeti_springboot_mongodb.services.usuarios;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.models.dto.UsuarioDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class UsuarioAutenticadoHelper {
    public UsuarioDTO usuarioAutenticado() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()){
            Object principal = auth.getPrincipal();
            if (principal  instanceof Usuario) {
                Usuario usuario = (Usuario) principal;
                return new UsuarioDTO(usuario.getId(), usuario.getNombre(), usuario.getApellidos(), usuario.getEmail());
            }
        }
        return null;
    }
}
