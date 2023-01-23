
package com.dappermoose.stsimplefinance.security;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AbstractAuthenticationEvent;
import org.springframework.security.authentication.event.AbstractAuthenticationFailureEvent;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.UserDetails;

import com.dappermoose.stsimplefinance.dao.LoginEventRepository;
import com.dappermoose.stsimplefinance.dao.LoginUserRepository;
import com.dappermoose.stsimplefinance.data.LoginEvent;
import com.dappermoose.stsimplefinance.data.LoginUser;
import com.dappermoose.stsimplefinance.data.YesNoEnum;

import lombok.extern.log4j.Log4j2;

/**
 * listening for authentication events and logging them to the login event table.
 *
 * @author matthewheitker
 */
@Log4j2
public class AuthenticationListener implements ApplicationListener<AbstractAuthenticationEvent>
{
    @Inject
    private LoginUserRepository loginUserRepository;

    @Inject
    private LoginEventRepository loginEventRepository;

    @Override
    @Transactional
    public void onApplicationEvent (final AbstractAuthenticationEvent e)
    {
        log.debug ("login event + " + e.toString ());

        if (!(e instanceof AuthenticationSuccessEvent ||
                e instanceof AbstractAuthenticationFailureEvent))
        {
            return;
        }

        Object obj = e.getAuthentication ().getPrincipal ();

        String userName;
        if (obj instanceof String)
        {
            userName = (String) obj;
        }
        else if (obj instanceof UserDetails)
        {
            userName = ((UserDetails) obj).getUsername ();
        }
        else
        {
            userName = "";
        }

        List<LoginUser> luser = loginUserRepository.findByUserName (userName);

        if (luser.size () <= 0)
        {
            return;
        }

        LoginUser user = luser.get (0);

        LoginEvent loginEvent = new LoginEvent ();

        loginEvent.setUser (user);

        if (e instanceof AbstractAuthenticationFailureEvent)
        {
            loginEvent.setSuccess (YesNoEnum.NO);
        }
        else
        {
            loginEvent.setSuccess (YesNoEnum.YES);
        }
        loginEventRepository.save (loginEvent);
    }
}
