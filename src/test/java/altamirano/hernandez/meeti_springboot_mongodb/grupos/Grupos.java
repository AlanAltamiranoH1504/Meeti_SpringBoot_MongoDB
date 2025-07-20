package altamirano.hernandez.meeti_springboot_mongodb.grupos;

import altamirano.hernandez.meeti_springboot_mongodb.models.Grupo;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IGrupoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataMongoTest
public class Grupos {
    @Autowired
    private IGrupoRepository iGrupoRepository;

    @Test
    @DisplayName("Busqueda de grupos por id de usuario")
    void findByUserId() {
        String id = "686b0df5b7c6b35cf30159cb";

        List<Grupo> gruposPorUsarioId = iGrupoRepository.findByUserId(id);
        assertThat(gruposPorUsarioId.size() > 0).isTrue();
    }

}
