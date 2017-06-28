package org.launchcode.onlinebankapp.models.data;

import org.launchcode.onlinebankapp.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

/**
 * Created by User on 6/27/17.
 */
@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {
    User findByUsername (String username);
    User findByEmail(String email);

}
