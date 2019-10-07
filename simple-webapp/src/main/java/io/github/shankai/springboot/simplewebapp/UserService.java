package io.github.shankai.springboot.simplewebapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UserService
 */
@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Iterable<User> getUsers() {
        Iterable<User> users = userRepository.findAll();
        return users;
    }

    public boolean createUser(User user) {
        userRepository.save(user);
        return true;
    }

    public boolean updateUser(User user) {
        userRepository.save(user);
        return true;
    }

    public boolean deleteUser(final String id) {
        userRepository.deleteById(id);
        return true;
    }
    
}