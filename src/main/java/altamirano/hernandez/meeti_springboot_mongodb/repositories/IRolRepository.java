package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface IRolRepository extends MongoRepository<Rol, String> {
}
