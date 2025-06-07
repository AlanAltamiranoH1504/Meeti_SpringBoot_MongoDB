package altamirano.hernandez.meeti_springboot_mongodb.services.usuarios;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IUsuarioRepository;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplUsuarioService implements IUsuarioService {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Override
    public List<Usuario> findAll() {
        try {
            List<Usuario> usuarios = iUsuarioRepository.findAll();
            return usuarios;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Usuario findById(String id) {
        try {
            Usuario usuario = iUsuarioRepository.findById(id).get();
            return usuario;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Usuario> findByUsername(String username) {
        try {
            Optional<Usuario> usuario = iUsuarioRepository.findByUsername(username);
            if (usuario.isPresent()) {
                return usuario;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        try {
            Optional<Usuario> usuario = iUsuarioRepository.findByEmail(email);
            if (usuario.isPresent()) {
                return usuario;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Usuario> findByToken(String token) {
        try {
            Optional<Usuario> usuario = iUsuarioRepository.findByToken(token);
            if (usuario.isPresent()) {
                return usuario;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Usuario usuario) {
        try {
            iUsuarioRepository.save(usuario);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            iUsuarioRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
