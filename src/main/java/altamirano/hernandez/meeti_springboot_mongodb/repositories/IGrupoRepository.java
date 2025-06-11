package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IGrupoRepository extends MongoRepository<Grupo, String> {
}
