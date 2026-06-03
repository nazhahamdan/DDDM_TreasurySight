package com.example.demo.Controller;

import com.example.demo.Entities.Entreprise;
import com.example.demo.Entities.User;
import com.example.demo.Service.AuthService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(AuthService authService, BCryptPasswordEncoder passwordEncoder){
        this.authService=authService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody LoginRequest loginRequest){
        String token = authService.login(
                loginRequest.getEmail(),
                loginRequest.getPassword()
        );
        return new AuthResponse(token);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest){

        User user = new User();
        Entreprise entreprise=new Entreprise();
        user.setNom(registerRequest.getNom());
        user.setPrenom(registerRequest.getPrenom());
        user.setEmail(registerRequest.getEmail());
        // Copier le mot de passe depuis la requête
        user.setPassword(registerRequest.getPassword());

        entreprise.setNom(registerRequest.getNomEntreprise());
        entreprise.setSecteur(registerRequest.getSecteurEntreprise());
        user.setPassword(registerRequest.getPassword());
        String token = authService.register(user, registerRequest.getNomEntreprise());

        return new AuthResponse(token);
    }
}
