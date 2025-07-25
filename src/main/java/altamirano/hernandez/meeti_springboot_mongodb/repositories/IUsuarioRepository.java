package altamirano.hernandez.meeti_springboot_mongodb.repositories;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface IUsuarioRepository extends MongoRepository<Usuario, String> {

    //TODO: Modificar para considerar la confirmacion de cuenta en el backend
//    @Query("{'email': ?0, 'confirmado': true}")
//    public Optional<Usuario> findByEmail(String email);
    @Query("{'email': ?0}")
    public Optional<Usuario> findByEmail(String email);

    @Query("{'username': ?0}")
    public Optional<Usuario> findByUsername(String username);

    @Query("{'token':  ?0}")
    public Optional<Usuario> findByToken(String token);
}
