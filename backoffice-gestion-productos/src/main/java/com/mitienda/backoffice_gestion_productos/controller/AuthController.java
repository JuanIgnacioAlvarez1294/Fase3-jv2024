package com.mitienda.backoffice_gestion_productos.controller;

import com.mitienda.backoffice_gestion_productos.dto.AuthRequest; // Usa el DTO de AuthRequest
import com.mitienda.backoffice_gestion_productos.service.UserService;
import com.mitienda.backoffice_gestion_productos.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @PostMapping("/api/login")
    public String login(@RequestBody AuthRequest authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Contraseña incorrecta", e);
        } catch (UsernameNotFoundException e) {
            throw new Exception("Usuario no encontrado", e);
        } catch (Exception e) {
            throw new Exception("Error en la autenticación", e);
        }


        final UserDetails userDetails = userService.loadUserByUsername(authRequest.getUsername());
        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return jwt;
    }
}