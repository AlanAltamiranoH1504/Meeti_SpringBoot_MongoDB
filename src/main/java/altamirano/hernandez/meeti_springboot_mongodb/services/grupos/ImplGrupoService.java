package altamirano.hernandez.meeti_springboot_mongodb.services.grupos;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IGrupoRepository;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.IGrupoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImplGrupoService implements IGrupoService {
    @Autowired
    private IGrupoRepository iGrupoRepository;

    @Override
    public List<Grupo> findAll() {
        try {
            List<Grupo> grupos = iGrupoRepository.findAll();
            return grupos;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Grupo> findById(String id) {
        try {
            Optional<Grupo> grupo = iGrupoRepository.findById(id);
            return grupo;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Grupo save(Grupo grupo) {
        try {
            Grupo grupoSaved = iGrupoRepository.save(grupo);
            return grupoSaved;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(String id) {
        try {
            iGrupoRepository.deleteById(id);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }
}
