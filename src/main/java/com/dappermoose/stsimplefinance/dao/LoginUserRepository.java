package com.dappermoose.stsimplefinance.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

import com.dappermoose.stsimplefinance.data.LoginUser;


// TODO: Auto-generated Javadoc
/**
 * The Interface LoginUserRepository.
 */
public interface LoginUserRepository extends CrudRepository<LoginUser, Long>,
                                     RevisionRepository<LoginUser, Long, Long>
{

    /**
     * Find by user name.
     *
     * @param userName the user name
     * @return the list
     */
    List<LoginUser> findByUserName (String userName);
}
