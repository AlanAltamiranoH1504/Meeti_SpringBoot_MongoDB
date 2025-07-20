package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Meeti;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IMeetiRepository extends CrudRepository<Meeti, String> {
    @Query("{usuario_id: ?0}")
    List<Meeti> findAllByUserId(String id);
}
