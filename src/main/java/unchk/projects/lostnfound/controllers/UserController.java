package unchk.projects.lostnfound.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.requests.LoginRequest;
import unchk.projects.lostnfound.requests.RegisterRequest;
import unchk.projects.lostnfound.services.UserService;

@RestController
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

        userService.saveUser(user);

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        Users user = userService.findUserByEmailAndPassword(loginRequest);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).build();
        }
    }

}
