package com.dappermoose.stsimplefinance.security;

import java.util.List;

import jakarta.inject.Inject;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.dappermoose.stsimplefinance.dao.LoginUserRepository;
import com.dappermoose.stsimplefinance.data.LoginUser;
import com.dappermoose.stsimplefinance.data.YesNoEnum;

/**
 *  Implements the authentication provider to hit the database tables.
 *
 * @author matthewheitker
 */
@Service ("authService")
public class UserDetailsDaoImpl implements UserDetailsService
{
    @Inject
    private LoginUserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername (final String userName) throws UsernameNotFoundException
    {
        List<LoginUser> users = userRepository.findByUserName (userName);

        if (users.size () <= 0)
        {
            throw new UsernameNotFoundException ("User Name " + userName + " not found.");
        }

        LoginUser luser = users.get (0);

        UserDetails user = new User (luser.getUserName (), luser.getPassword (),
                YesNoEnum.toBoolean (luser.getEnabled ()),
                true, true, true, AuthorityUtils.NO_AUTHORITIES);

        return user;
    }
}
