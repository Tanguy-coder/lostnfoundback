package unchk.projects.lostnfound.services;

import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Roles;

import java.util.List;

@Service
public interface RoleService {
    Roles addRole(Roles role);
    Roles updateRole(Roles role);
    void deleteRole(Roles role);
    void deleteRoleById(Long id);
    Roles getRoleById(Long id);
    List<Roles> getRoles();
}
