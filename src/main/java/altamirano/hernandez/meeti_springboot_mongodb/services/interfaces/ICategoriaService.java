package altamirano.hernandez.meeti_springboot_mongodb.services.interfaces;

import altamirano.hernandez.meeti_springboot_mongodb.models.Categoria;

import java.util.List;
import java.util.Optional;

public interface ICategoriaService {
    public List<Categoria> getCategorias();
    public abstract Optional<Categoria> findById(String id);
    public abstract Categoria save(Categoria categoria);
    public abstract void deleteById(String id);
}
