package altamirano.hernandez.meeti_springboot_mongodb.roles;

import altamirano.hernandez.meeti_springboot_mongodb.models.Rol;
import altamirano.hernandez.meeti_springboot_mongodb.models.Usuario;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IRolRepository;
import altamirano.hernandez.meeti_springboot_mongodb.repositories.IUsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataMongoTest
public class Roles {

    @Autowired
    private IRolRepository iRolRepository;
    @Autowired
    private IUsuarioRepository iUsuarioRepository;

    @Test
    void testFindRolByNombre() {
        String nombreRol = "USER";
        Rol rol = iRolRepository.findByName(nombreRol);
        assertNotNull(rol, "No se encontro un rol con nombre: " + nombreRol);
    }

    @Test
    void rolesDeUsuario() {
        String email = "alan@gmail.com";
        Optional<Usuario> usuario = iUsuarioRepository.findByEmail(email);

        assertThat(usuario.isPresent());

        List<Rol> rolesDelUsuarioObjetos = usuario.get().getRoles();
        assertTrue(rolesDelUsuarioObjetos.size() > 0, "El usuario no tiene roles asignados");

        List<String> rolesString = new ArrayList<>();
        for (var rol: rolesDelUsuarioObjetos){
            rolesString.add(rol.getNombre());
        }

        for (var roleString: rolesString){
            System.out.println("roleString = " + roleString);
        }

        assertTrue(rolesString.size() > 0, "No se guardaron correctamente los errores en String");
    }
}
