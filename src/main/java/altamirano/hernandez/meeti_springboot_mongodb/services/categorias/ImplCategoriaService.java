package altamirano.hernandez.meeti_springboot_mongodb.services.categorias;

import altamirano.hernandez.meeti_springboot_mongodb.models.Categoria;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.ICategoriaRepository;
import altamirano.hernandez.meeti_springboot_mongodb.services.interfaces.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ImplCategoriaService implements ICategoriaService {
    @Autowired
    private ICategoriaRepository categoriaRepository;
    @Override
    public List<Categoria> getCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> findById(String id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public void deleteById(String id) {

        categoriaRepository.deleteById(id);
    }
}
