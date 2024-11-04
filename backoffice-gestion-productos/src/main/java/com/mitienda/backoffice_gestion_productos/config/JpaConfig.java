package com.mitienda.backoffice_gestion_productos.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.mitienda.backoffice_gestion_productos.repository")
public class JpaConfig {
    // Esta clase está vacía porque solo estamos habilitando los repositorios JPA.
    // Puedes agregar configuraciones adicionales aquí si lo necesitas en el futuro.
}
