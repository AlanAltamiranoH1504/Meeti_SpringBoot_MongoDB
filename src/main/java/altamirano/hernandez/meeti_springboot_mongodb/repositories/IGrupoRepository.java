package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IGrupoRepository extends MongoRepository<Grupo, String> {

    @Query("{'usuario._id': ?0}")
    List<Grupo> findByUserId(String userId);

    @Query("{'_id': ?0, 'usuario._id': ?1}")
    Optional<Grupo> findByIdAndUserId(String id, String userId);
}
