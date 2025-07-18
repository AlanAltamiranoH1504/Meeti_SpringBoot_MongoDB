package altamirano.hernandez.meeti_springboot_mongodb.services.interfaces;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;

import java.util.List;
import java.util.Optional;

public interface IGrupoService {
    public abstract List<Grupo> findAll();
    public abstract Optional<Grupo> findById(String id);
    public abstract List<Grupo> findByUserId(String userId);
    public abstract Optional<Grupo> findByIdAndUserId(String id, String userId);
    public abstract Grupo save(Grupo grupo);
    public abstract void deleteById(String id);
}
