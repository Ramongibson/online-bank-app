package org.launchcode.onlinebankapp.service;

import org.launchcode.onlinebankapp.models.User;
import org.launchcode.onlinebankapp.models.security.UserRole;

import java.util.Set;

/**
 * Created by User on 6/27/17.
 */
public interface UserService {
    User findByUsername(String username);

    User findByEmail(String email);

    boolean checkUserExists(String username, String email);

    boolean checkUsernameExists(String username);

    boolean checkEmailExists(String email);

    void save(User user);

    User createUser(User user, Set<UserRole> userRoles);

}
