package altamirano.hernandez.meeti_springboot_mongodb.security;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {
    @Autowired
    private IUsuarioService iUsuarioService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Usuario> usuario = iUsuarioService.findByEmail(email);
        //Verificacion usuario encontrado
        if (!usuario.isPresent()) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        Usuario usuarioDB = usuario.get();

        //Configuracion de permisos del usuario
        List<Rol> rolesDelUsuarioEnObjeto = usuarioDB.getRoles();
        List<GrantedAuthority> permisos = new ArrayList<>();
        List<String> permisosString = new ArrayList<>();
        for (var rol: rolesDelUsuarioEnObjeto){
            permisosString.add(rol.getNombre());
        }
        for (var rolString: permisosString) {
            permisos.add(new SimpleGrantedAuthority("ROLE_" + rolString));
        }

        if (permisos.isEmpty()) {
            throw  new UsernameNotFoundException("El usuario: " + usuarioDB.getNombre() + " no tiene permisos asignados");
        }

        System.out.println("PERMISOS DEL USUARIO: ");
        System.out.println(permisos);
        System.out.println("Inicio sesion corractamente");
        return new User(usuarioDB.getEmail(), usuarioDB.getPassword(), true, true, true, true, permisos);
    }
}
