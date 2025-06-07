package altamirano.hernandez.meeti_springboot_mongodb.usuarios;

import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IUsuarioRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;



@DataMongoTest
public class Usuarios {

    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Test
    void notFindUsuarioByUsername() {
        Optional<Usuario> usuarioBusqueda = iUsuarioRepository.findByUsername("cholofake");
        assertNull(usuarioBusqueda, "Hay un usuario regisrado con ese username");
    }

    @Test
    void findUsuarioByUsername() {
        Optional<Usuario> usuarioBusqueda = iUsuarioRepository.findByUsername("cholofake");
        assertNotNull(usuarioBusqueda, "No hay un usuario regisrado con ese username");
    }

    @Test
    @Disabled
    void findUsuarioByToken(){
        Optional<Usuario> usuarioBusqueda = iUsuarioRepository.findByToken("acde8fdc-15cf-4dce-a6a3-e2761f8cfbf3");
        assertThat(usuarioBusqueda).isPresent();
    }
}
