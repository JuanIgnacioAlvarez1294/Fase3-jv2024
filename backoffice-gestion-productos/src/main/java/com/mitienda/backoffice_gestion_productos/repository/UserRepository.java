package com.mitienda.backoffice_gestion_productos.repository;

import com.mitienda.backoffice_gestion_productos.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
