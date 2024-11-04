package com.mitienda.backoffice_gestion_productos.controller;

import com.mitienda.backoffice_gestion_productos.model.User;
import com.mitienda.backoffice_gestion_productos.repository.UserRepository;
import com.mitienda.backoffice_gestion_productos.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public String registerUser(@RequestBody RegisterRequest request) {
        // Verificar si ya existe un usuario con el mismo nombre
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            return "El nombre de usuario ya está en uso";
        }

        // Codifica la contraseña antes de guardarla
        User newUser = new User(
                request.getUsername(),
                passwordEncoder.encode(request.getPassword()),
                request.getRole()  // El rol viene del cuerpo de la solicitud
        );
        userRepository.save(newUser);
        return "Usuario registrado exitosamente";
    }
}

class RegisterRequest {
    private String username;
    private String password;
    private Role role;  // Aceptar el rol en el cuerpo de la solicitud

    // Getters y setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}