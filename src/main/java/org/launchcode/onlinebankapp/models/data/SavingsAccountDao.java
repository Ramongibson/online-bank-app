package org.launchcode.onlinebankapp.models.data;

import org.launchcode.onlinebankapp.models.SavingsAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * Created by User on 7/05/17.
 */
@Repository
@Transactional
public interface SavingsAccountDao extends CrudRepository<SavingsAccount, Long> {

    SavingsAccount findByAccountNumber (int accountNumber);
}

