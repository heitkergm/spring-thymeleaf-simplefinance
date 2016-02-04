package com.dappermoose.stsimplefinance.dao;

import org.springframework.data.repository.CrudRepository;

import com.dappermoose.stsimplefinance.data.LoginEvent;

/**
 * The Interface LoginEventRepository.
 */
public interface LoginEventRepository extends CrudRepository<LoginEvent, Long>
{
}
