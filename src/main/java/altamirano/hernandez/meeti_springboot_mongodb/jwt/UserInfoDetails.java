package altamirano.hernandez.meeti_springboot_mongodb.jwt;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserInfoDetails implements UserDetails {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String password;
    private List<GrantedAuthority> authorities;

    public UserInfoDetails(Usuario usuario) {
        nombre = usuario.getNombre();
        password = usuario.getPassword();
        authorities = Arrays.stream(usuario.getNombre().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return nombre;
    }
}
