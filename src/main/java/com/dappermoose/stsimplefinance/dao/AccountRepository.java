package com.dappermoose.stsimplefinance.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dappermoose.stsimplefinance.data.Account;
import com.dappermoose.stsimplefinance.data.LoginUser;

// TODO: Auto-generated Javadoc
/**
 * The Interface AccountRepository.
 */
public interface AccountRepository extends CrudRepository<Account, Long>
{

    /**
     * Find by user.
     *
     * @param user the user
     * @return the list
     */
    List<Account> findByUser (LoginUser user);
}
