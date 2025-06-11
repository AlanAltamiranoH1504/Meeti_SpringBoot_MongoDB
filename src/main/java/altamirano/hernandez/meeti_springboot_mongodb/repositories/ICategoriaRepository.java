package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Categoria;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ICategoriaRepository extends MongoRepository<Categoria, String> {
}
