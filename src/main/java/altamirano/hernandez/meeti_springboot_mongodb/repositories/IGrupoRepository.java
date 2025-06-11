package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface IGrupoRepository extends MongoRepository<Grupo, String> {

    @Query("{'usuario._id': ?0}")
    List<Grupo> findByUserId(String userId);
}
