package unchk.projects.lostnfound.implement;

import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Roles;
import unchk.projects.lostnfound.repos.RoleRepository;
import unchk.projects.lostnfound.services.RoleService;

import java.util.List;

@Service
public class RoleServiceImplement implements RoleService {
    RoleRepository roleRepository;

    @Override
    public Roles addRole(Roles role) {
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
        return roleRepository.findById(id).get();
    }

    @Override
    public List<Roles> getRoles() {
        return roleRepository.findAll();
    }
}
