package com.dappermoose.stsimplefinance.action;

import java.util.List;
import java.util.TimeZone;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dappermoose.stsimplefinance.dao.LoginUserRepository;
import com.dappermoose.stsimplefinance.data.LoginUser;
import com.dappermoose.stsimplefinance.formbean.RegisterUser;

// TODO: Auto-generated Javadoc
/**
 * The Class LoginAction.
 */
@Controller
public class RegisterAction
{

    /** The user repository. */
    @Inject
    private LoginUserRepository userRepository;

    /** The message source. */
    @Inject
    private MessageSource messageSource;

    @Inject
    private ApplicationContext context;

    /**
     * Main action. Display the form.
     *
     * @param model the model
     * @return the string
     */
    @RequestMapping (value = "/register", method = RequestMethod.GET)
    public String mainAction (final Model model)
    {
        RegisterUser ru = new RegisterUser ();
        ru.setTzone (TimeZone.getDefault ().getID ());
        model.addAttribute ("register", ru);
        model.addAttribute ("tzones", context.getBean ("tzones"));
        return "register";
    }

    /**
     * Process register action.
     *
     * @param register the register
     * @param res the res
     * @param model the model
     * @param request the request
     * @return the string
     */
    @Transactional
    @RequestMapping (value = "/register", method = RequestMethod.POST)
    public String processRegisterAction (
            @Valid @ModelAttribute ("register") final RegisterUser register,
            final BindingResult res, final Model model,
            final HttpServletRequest request)
    {
        if (res.hasErrors ())
        {
            model.addAttribute ("register", register);
            model.addAttribute ("tzones", context.getBean ("tzones"));
            return "register";
        }

        // check if the user already exists
        final List<LoginUser> users = userRepository.findByUserName (register
                .getUserName ());
        if (users.size () > 0)
        {
            final ObjectError objErr = new ObjectError ("RegisterUser",
                    messageSource.getMessage ("register.duplicateUser", null,
                            request.getLocale ()));
            res.addError (objErr);
            model.addAttribute ("register", register);
            model.addAttribute ("tzones", context.getBean ("tzones"));
            return "register";
        }

        // create user bean
        final LoginUser user = new LoginUser ();
        user.setUserName (register.getUserName ());
        user.setPassword (register.getPassword ());
        user.setTzone (register.getTzone ());
        userRepository.save (user);

        return "redirect:/main";
    }
}
