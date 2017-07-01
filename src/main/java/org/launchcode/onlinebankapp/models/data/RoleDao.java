package org.launchcode.onlinebankapp.models.data;

import org.launchcode.onlinebankapp.models.security.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by User on 7/1/17.
 */
@Repository
@Transactional
public interface RoleDao extends CrudRepository<Role, Integer> {
    Role findByName(String name);
}