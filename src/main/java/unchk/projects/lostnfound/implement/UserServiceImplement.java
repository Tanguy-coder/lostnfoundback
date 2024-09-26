package unchk.projects.lostnfound.implement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import unchk.projects.lostnfound.models.Users;
import unchk.projects.lostnfound.repos.UserRepository;
import unchk.projects.lostnfound.requests.LoginRequest;
import unchk.projects.lostnfound.services.UserService;

import java.util.List;

@Service
public class UserServiceImplement implements UserService {

    @Autowired
    UserRepository userRepository;
    @Override
    public Users findUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public Users findUserByEmailAndPassword(LoginRequest loginRequest) {
        Users user = userRepository.findByEmailAndPassword(loginRequest.getEmail(),loginRequest.getPassword());
        if (user != null && user.getPassword().equals(loginRequest.getPassword())) {
            return user;
        }
        return null; // Renvoie null si les informations d'identification ne correspondent pas
    }


    
    @Override
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public Users updateUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Users user) {
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Users getUserById(Long id) {
        return userRepository.getReferenceById(id);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }
}
