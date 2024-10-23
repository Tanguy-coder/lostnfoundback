package unchk.projects.lostnfound.implement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.services.RoleService;
import java.util.List;
import java.util.Optional;
import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {

    private final RoleRepository roleRepository;

    // Constructor with @Autowired to inject the repository
    @Autowired
    public RoleServiceImplement(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Roles addRole(Roles role) {
    	 // Vérification si le rôle existe déjà
        Optional<Roles> existingRole = Optional.ofNullable((roleRepository.findByName(role.getName())));
        if (existingRole.isPresent()) {
            // Si le rôle existe, on peut renvoyer null ou déclencher une exception
            throw new IllegalStateException("Le rôle existe déjà.");
        }
        return roleRepository.save(role);
    }

    @Override
    public Roles updateRole(Roles role) {
        return roleRepository.save(role);
    }

    @Override
    public void deleteRole(Roles role) {
        roleRepository.delete(role);
    }

    @Override
    public void deleteRoleById(Long id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Roles getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    @Override
    public List<Roles> getRoles() {
        return roleRepository.findAll();
    }

}
