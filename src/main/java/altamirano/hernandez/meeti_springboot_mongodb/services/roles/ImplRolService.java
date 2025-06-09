package altamirano.hernandez.meeti_springboot_mongodb.services.roles;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IRolRepository;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IRolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplRolService implements IRolService {

    @Autowired
    private IRolRepository iRolRepository;

    @Override
    public List<Rol> findAll() {
        try {
            List<Rol> roles = iRolRepository.findAll();
            return roles;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Rol> findById(String id) {
        try {
            Optional<Rol> rol = iRolRepository.findById(id);
            if (rol.isPresent()) {
                return rol;
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void save(Rol rol) {
        try {
            iRolRepository.save(rol);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        Optional<Rol> rol =iRolRepository.findById(id);
        if (rol.isPresent()){
            iRolRepository.deleteById(rol.get().getId());
        }
    }
}
