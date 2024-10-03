package unchk.projects.lostnfound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.requests.LoginRequest;
import unchk.projects.lostnfound.requests.RegisterRequest;
import unchk.projects.lostnfound.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
public class UserController {

    private final UserService userService;
    private final RoleRepository roleRepository;

    @Autowired
    public UserController(UserService userService, RoleRepository roleRepository) {
        this.userService = userService;
        this.roleRepository = roleRepository;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        Roles role = roleRepository.findByName(registerRequest.getRole());

        Users user = new Users();
        user.setName(registerRequest.getName());
        user.setPassword(registerRequest.getPassword()); // Assurez-vous de hacher le mot de passe
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getTelephone());
        user.setUsername(registerRequest.getUsername());
        user.setRole(role);
        
        try {
            userService.saveUser(user);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "User registered successfully"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", e.getMessage()));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Users user = userService.findUserByEmailAndPassword(loginRequest);
        if (user != null) {
        	System.out.println("nom d'utilisateur"+user.getUsername());
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password."));
        }
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/roles")
    public List<Roles> getRoles() {
        return roleRepository.findAll();
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping({"/users"})
    public List<Users> getUsers() {
        return userService.getAllUsers();
    }

    // Récupérer les informations d'un utilisateur par son nom d'utilisateur
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/user/{userId}")
    public ResponseEntity<Users> getUser(@PathVariable("userId") long id) {
        Users user = userService.findUserById(id);
        System.out.println("a la recuperation"+id);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // ou un message d'erreur si nécessaire
        }
    }

    // Mettre à jour les informations d'un utilisateur
    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping("/user/{userId}/valid")
    public ResponseEntity<Users> updateUser(@PathVariable("userId") long id, @RequestBody Users user) {
        Users currentUser = userService.findUserById(id);
        System.out.println("currently"+currentUser);
        if (currentUser != null) {
            // Mettre à jour les champs de l'utilisateur
            currentUser.setUsername(user.getUsername());
            currentUser.setEmail(user.getEmail());
            currentUser.setPhone(user.getPhone());
            currentUser.setName(user.getName());
            currentUser.setPassword(user.getPassword());
            // Vous pouvez gérer le mot de passe ici (hashing, etc.)

            Users updatedUser = userService.updateUser(currentUser);
            return ResponseEntity.ok(updatedUser);
        } else {
        	System.out.println("requete perçu");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(null); // ou un message d'erreur si nécessaire
        }
    }
}
