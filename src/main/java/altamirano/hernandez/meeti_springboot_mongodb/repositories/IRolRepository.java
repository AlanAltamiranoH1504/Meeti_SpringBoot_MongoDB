package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface IRolRepository extends MongoRepository<Rol, String> {

    @Query("{'nombre' : ?0}")
    public Rol findByName(String nombre);
}
