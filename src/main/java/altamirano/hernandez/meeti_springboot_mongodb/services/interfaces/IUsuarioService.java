package altamirano.hernandez.meeti_springboot_mongodb.services.interfaces;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {
    public abstract List<Usuario> findAll();
    public abstract Usuario findById(String id);
    public abstract Optional<Usuario> findByUsername(String username);
    public abstract Optional<Usuario> findByEmail(String email);
    public abstract Optional<Usuario> findByToken(String token);
    public abstract void save(Usuario usuario);
    public abstract void deleteById(String id);
}

