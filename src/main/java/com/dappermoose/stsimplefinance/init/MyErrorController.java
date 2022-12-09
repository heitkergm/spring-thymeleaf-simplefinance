package com.dappermoose.stsimplefinance.init;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// TODO: Auto-generated Javadoc
/**
 * The Class MyErrorController.
 */
@Controller
public class MyErrorController implements ErrorController
{
    /**
     * Error action.
     *
     * @param request - the servlet request
     * @param model the model
     * @return the string
     */
    @RequestMapping ("/error")
    public String myErrorAction (final HttpServletRequest request,
            final Model model)
    {
        model.addAttribute ("status",
                request.getAttribute ("jakarta.servlet.error.status_code"));
        model.addAttribute ("reason",
                request.getAttribute ("jakarta.servlet.error.message"));

        return "httpError";
    }
}
