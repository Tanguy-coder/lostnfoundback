package unchk.projects.lostnfound.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.services.RoleService;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/roles")

public class RoleController {

    private final RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    // End-point pour ajouter un rôle
    @PostMapping("/create")
    public ResponseEntity<Roles> addRole(@RequestBody Roles role) {
    	 System.out.println("Reçu: " + role.getName());
        // Vérifie que le champ 'name' est bien renseigné
        if (role.getName() == null || role.getName().isEmpty()) {
        	
            return ResponseEntity.badRequest().body(null); // Retourne une erreur si le nom est vide
        }

        Roles savedRole = roleService.addRole(role);
        return ResponseEntity.ok(savedRole);
    }

   

}
