package org.launchcode.onlinebankapp.services;

import org.launchcode.onlinebankapp.models.User;

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
}
