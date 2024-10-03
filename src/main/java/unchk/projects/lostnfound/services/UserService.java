package unchk.projects.lostnfound.services;

import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.requests.LoginRequest;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {
    Users findUserById(Long id);
    Users findUserByEmailAndPassword(LoginRequest loginRequest);
    Users saveUser(Users user);
    Users updateUser(Users user);
    void deleteUser(Users user);
    void deleteUserById(Long id);
    Users getUserById(Long id);
    List<Users> getAllUsers();
    Users getAuthenticatedUser(String username);
    Users findByUsername(String username);
	
}
