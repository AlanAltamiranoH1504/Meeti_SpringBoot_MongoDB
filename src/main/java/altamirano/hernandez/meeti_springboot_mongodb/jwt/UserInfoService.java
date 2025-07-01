package altamirano.hernandez.meeti_springboot_mongodb.jwt;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = iUsuarioService.findByEmail(email);

        return usuario.map(UserInfoDetails::new).orElseThrow(() -> new UsernameNotFoundException("Usuario con email " + email + " no encontrado"));
    }
}
