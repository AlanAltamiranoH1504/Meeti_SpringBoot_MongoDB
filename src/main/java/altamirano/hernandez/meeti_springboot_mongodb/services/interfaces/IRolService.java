package altamirano.hernandez.meeti_springboot_mongodb.services.interfaces;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;

import java.util.List;
import java.util.Optional;

public interface IRolService {
    public abstract List<Rol> findAll();
    public abstract Optional<Rol> findById(String id);
    public abstract Rol findRolByNombre(String nombre);
    public abstract void save(Rol rol);
    public abstract void deleteById(String id);
}
