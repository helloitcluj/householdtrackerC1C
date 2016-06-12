package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.services.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;


@Controller
public class HomeController {

    private static final Logger LOGGER = LogManager.getLogger(HomeController.class);
    private static final String HOME = "homepage";


    @RequestMapping(path = "/", method = RequestMethod.GET)
    public String home(final HttpSession session) {

        final String result;

        final Object userName = session.getAttribute(AccountController.CURRENT_PRINCIPAL_TAG);

        if (userName != null) {
            result = HOME;
        } else {
            result = "redirect:/account/login_account.html";
        }

        return result;
    }


}
