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
        String id = "68471a11a0b527b3b9f4664d";

        List<Grupo> gruposPorUsarioId = iGrupoRepository.findByUserId(id);
        assertThat(gruposPorUsarioId.size() > 0).isTrue();
    }

//    @Test
//    @DisplayName("Busqueda de grupo por id y por usuario id")
//    void findByIdAndUserId() {
//        String id = "6851eb7661fec2de93b231b5";
//        String userId = "68471a11a0b527b3b9f4664d";
//
//    }
}
