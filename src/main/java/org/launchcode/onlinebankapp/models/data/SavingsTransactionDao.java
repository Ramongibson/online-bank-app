package org.launchcode.onlinebankapp.models.data;

import org.launchcode.onlinebankapp.models.SavingsTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by User on 7/15/17.
 */

@Repository
@Transactional
public interface SavingsTransactionDao extends CrudRepository<SavingsTransaction, Long> {
    List<SavingsTransaction> findAll();
}