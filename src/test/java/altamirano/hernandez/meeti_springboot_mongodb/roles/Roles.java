package altamirano.hernandez.meeti_springboot_mongodb.roles;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IRolRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class Roles {

    @Autowired
    private IRolRepository iRolRepository;

    @Test
    void testFindRolByNombre() {
        String nombreRol = "USER";
        Rol rol = iRolRepository.findByName(nombreRol);
        assertNotNull(rol, "No se encontro un rol con nombre: " + nombreRol);
    }
}
