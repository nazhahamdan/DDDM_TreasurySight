package com.example.demo.Service;

import com.example.demo.Entities.Entreprise;
import com.example.demo.Entities.User;
import com.example.demo.Jwt.JwtService;
import com.example.demo.Repositories.EntrepriseRepository;
import com.example.demo.Repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AuthService {
    private final UserRepository userRepository;
    private final EntrepriseRepository entrepriseRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    @Autowired
    public AuthService(UserRepository userRepository,
                       EntrepriseRepository entrepriseRepository,
                       BCryptPasswordEncoder passwordEncoder,
                       JwtService jwtService) {
        this.userRepository = userRepository;
        this.entrepriseRepository = entrepriseRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }
    public String register(User user, String nomEntreprise) {

        // Vérifier si l'email existe déjà
        Optional<User> existEmail = userRepository.findByEmail(user.getEmail());
        if (existEmail.isPresent()) {
            throw new RuntimeException("Cet email est déjà utilisé");
        }
        Optional<Entreprise> existEntreprise = entrepriseRepository.findByNom(nomEntreprise);
        if (existEntreprise.isPresent()) {
            throw new RuntimeException("Une entreprise avec ce nom existe déjà");
        }

        // créer l'entreprise
        Entreprise entreprise = new Entreprise();
        entreprise.setNom(nomEntreprise);
        entreprise = entrepriseRepository.save(entreprise);

        // Associer l'entreprise à l'utilisateur
        user.setEntreprise(entreprise);

        // Encoder le mot de passe
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Sauvegarder l'utilisateur
        userRepository.save(user);

        // Générer et retourner le JWT
        return jwtService.generateToken(user.getEmail());
    }
    public String login(String email, String password){
        Optional<User> existEmail = userRepository.findByEmail(email);
        System.out.println("EMAIL RECU = " + email);
        System.out.println("password recu: "+password);
        if (!existEmail.isPresent()){
            throw new RuntimeException("Utilisateur introuvable");
        }
        User user = existEmail.get();
        boolean passwordMatch = passwordEncoder.matches(password, user.getPassword());
        if (!passwordMatch){
            throw new RuntimeException("mot de passe incorrect");
        }
        // génération du JWT
        String token = jwtService.generateToken(user.getEmail());
        return token;
    }

}