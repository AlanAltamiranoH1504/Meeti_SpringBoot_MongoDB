package altamirano.hernandez.meeti_springboot_mongodb.services.interfaces;

import altamirano.hernandez.meeti_springboot_mongodb.models.Meeti;

import java.util.List;
import java.util.Optional;

public interface IMeetiService {
    public abstract List<Meeti> findAll();
    public abstract List<Meeti> findAllByUserId(String id);
    public abstract Optional<Meeti> findById(String id);
    public abstract void save(Meeti meeti);
    public abstract void deleteById(String id);
}
