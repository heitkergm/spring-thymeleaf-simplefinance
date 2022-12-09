package com.dappermoose.stsimplefinance.action;

import java.util.List;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dappermoose.stsimplefinance.dao.LoginUserRepository;
import com.dappermoose.stsimplefinance.data.LoginUser;
import com.dappermoose.stsimplefinance.formbean.ChangePwd;

import lombok.extern.log4j.Log4j2;

// TODO: Auto-generated Javadoc
/**
 * The Class ChangePasswordAction.
 */
@Controller
@Log4j2
public class ChangePasswordAction
{
    /** The user repository. */
    @Inject
    private LoginUserRepository userRepository;

    /** The message source. */
    @Inject
    private MessageSource messageSource;

    /** the time zones. */
    @Inject
    private ApplicationContext context;

    /**
     * Main action. Display the form.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping (value = "/changepwd", method = RequestMethod.GET)
    public String mainAction (final Model model)
    {
        final ChangePwd changepwd = new ChangePwd ();
        String userName = ((User) SecurityContextHolder.getContext ()
                .getAuthentication ().getPrincipal ()).getUsername ();

        List<LoginUser> users = userRepository.findByUserName (userName);

        LoginUser user = users.get (0);
        changepwd.setUserName (user.getUserName ());
        changepwd.setTzone (user.getTzone ());

        model.addAttribute ("changepwd", changepwd);
        model.addAttribute ("tzones", context.getBean ("tzones"));
        return "changepwd";
    }

    /**
     * Process change password action.
     *
     * @param changepwd the change password form data
     * @param res the res
     * @param model the model
     * @param request the request
     * @return the string
     */
    @Transactional
    @RequestMapping (value = "/changepwd", method = RequestMethod.POST)
    public String processRegisterAction (
            @Valid @ModelAttribute ("changepwd") final ChangePwd changepwd,
            final BindingResult res, final Model model,
            final HttpServletRequest request)
    {
        if (res.hasErrors ())
        {
            model.addAttribute ("changepwd", changepwd);
            model.addAttribute ("tzones", context.getBean ("tzones"));
            return "changepwd";
        }

        // check the current password, etc.
        String userName = ((User) SecurityContextHolder.getContext ()
                .getAuthentication ().getPrincipal ()).getUsername ();

        List<LoginUser> users = userRepository.findByUserName (userName);

        LoginUser user = users.get (0);

        if (!user.checkpw (changepwd.getCurrentPassword ()))
        {
            final ObjectError objErr = new ObjectError ("User",
                    messageSource.getMessage ("changepwd.currpwd.bad", null,
                            request.getLocale ()));
            res.addError (objErr);
            model.addAttribute ("changepwd", changepwd);
            model.addAttribute ("tzones", context.getBean ("tzones"));
            return "changepwd";
        }

        // modify user bean
        user.setPassword (changepwd.getPassword ());
        user.setTzone (changepwd.getTzone ());
        userRepository.save (user);

        // logout
        try
        {
            request.logout ();
        }
        catch (ServletException se)
        {
            LOG.info ("Servlet exception upon logout " + se.getMessage ());
        }

        return "redirect:/main";
    }
}
