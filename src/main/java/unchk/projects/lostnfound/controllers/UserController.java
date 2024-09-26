package unchk.projects.lostnfound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.repos.UserRepository;
import unchk.projects.lostnfound.requests.LoginRequest;
import unchk.projects.lostnfound.requests.RegisterRequest;
import unchk.projects.lostnfound.services.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = { "/", "/login", "/register"})
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
        user.setPassword(registerRequest.getPassword());
        user.setEmail(registerRequest.getEmail());
        user.setPhone(registerRequest.getTelephone());
        user.setUsername(registerRequest.getUsername());
        user.setRole(role);
        
        try {
            userService.saveUser(user);

            // Renvoie un objet JSON valide
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of("message", "User registered successfully")); // Utilisation de Map pour créer un objet JSON
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error", e.getMessage()));
        }
    }


    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Users user = userService.findUserByEmailAndPassword(loginRequest);
        System.out.println("utilisateur en connexion"+userService.findUserByEmailAndPassword(loginRequest));
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Invalid email or password."+user));
    }
    }

    

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/roles")
    public List <Roles> getRoles() {
        return roleRepository.findAll();
    }
    
    
    @GetMapping("/users")
    public List<Users> getUsers() {
    	System.out.println("liste est:"+userService.getAllUsers());
        return userService.getAllUsers();
    }

    }

