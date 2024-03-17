package org.example.service;

import org.example.entity.User;

import java.util.UUID;

public interface UserService {
    User getUserById(UUID userId);
    User getUserByUsername(String username);
    boolean isUserExistsByUsernameOrEmail(String username, String email);
    UUID saveUser(User user);
}
