package altamirano.hernandez.meeti_springboot_mongodb.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private AuthenticationConfiguration authenticationConfiguration;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .authorizeHttpRequests(auth -> auth
                        //Rutas que no requieren proteccion
                        .requestMatchers(HttpMethod.GET, "/crear-cuenta").permitAll()
                        .requestMatchers(HttpMethod.GET, "/confirmar-cuenta/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/crear-cuenta").permitAll()
                        .requestMatchers(HttpMethod.POST, "/register/confirmar-cuenta/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/iniciar-sesion/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/login").permitAll()


                        //Rutas que requieren proteccion
                        .requestMatchers(HttpMethod.GET, "/home/**").hasRole("USER")

                        //Liberacion archivos estaticos
                        .requestMatchers("/css/**", "/js/**", "/images/**", "/static/**").permitAll()

                        //Configuracion general
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/iniciar-sesion")
                        .permitAll()
                )
                .logout(Customizer.withDefaults());
        return http.build();
    }
}
