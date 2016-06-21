package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.services.IAccountService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("account")
public class AccountController {

    private static final Logger LOGGER = LogManager.getLogger(AccountController.class);

    public static final String ERROR = "error";
    public static final String REGISTER = "register";
    public static final String MISMATCHED_PASSWORD_MESSAGE = "The passwords you have provided doesn't match, please retype it!";

    public static final String ACCOUNT_EXISTS_MESSAGE = "The account already exist!";
    public static final String MESSAGE_TAG = "message";
    public static final String AUTHENTICATION_FAILURE_MESSAGE = "Authentication failure!";
    public static final String CURRENT_PRINCIPAL_TAG = "currentPrincipal";


    @Autowired
    private IAccountService accountService;

    @RequestMapping(path = "register", method = RequestMethod.POST)
    public String register(final String username, final String password, final String retypedPassword, final ModelMap model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Registering user " + username);
        }

        final String result;

        final IAccountService.CreationOutComes outCome = accountService.createAccount(username, password, retypedPassword);

        if (outCome == IAccountService.CreationOutComes.SUCCESS) {
            result = REGISTER;
        } else if (outCome == IAccountService.CreationOutComes.PASSWORD_DID_NOT_MATCH) {
            model.addAttribute(MESSAGE_TAG, MISMATCHED_PASSWORD_MESSAGE);
            result = ERROR;
        } else if (outCome == IAccountService.CreationOutComes.EXISTING_ACCOUNT) {
            model.addAttribute(MESSAGE_TAG, ACCOUNT_EXISTS_MESSAGE);
            result = ERROR;
        } else {
            throw new UnsupportedOperationException("Not supported case!");
        }

        return result;
    }

    @RequestMapping(path = "login", method = RequestMethod.POST)
    public String login(final String username, final String password, final ModelMap model, final HttpSession session) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Logging in user " + username);
        }

        final String result;

        final boolean outCome = accountService.authenticate(username, password);

        if (outCome) {
            result = "redirect:/";
            session.setAttribute(CURRENT_PRINCIPAL_TAG, username);

        } else {
            result = ERROR;
            model.addAttribute(MESSAGE_TAG, AUTHENTICATION_FAILURE_MESSAGE);
        }

        return result;
    }

    @RequestMapping(path = "registerAjax", method = RequestMethod.POST)
    public @ResponseBody String registerAjax(final String username, final String password, final String retypedPassword) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Registering user " + username);
        }

        final String result;

        final IAccountService.CreationOutComes outCome = accountService.createAccount(username, password, retypedPassword);

        if (outCome == IAccountService.CreationOutComes.SUCCESS) {
            result = null;
        } else if (outCome == IAccountService.CreationOutComes.PASSWORD_DID_NOT_MATCH) {
            result = MISMATCHED_PASSWORD_MESSAGE;
        } else if (outCome == IAccountService.CreationOutComes.EXISTING_ACCOUNT) {
            result = ACCOUNT_EXISTS_MESSAGE;
        } else {
            throw new UnsupportedOperationException("Not supported case!");
        }

        return result;
    }


    @RequestMapping(path = "loginAjax", method = RequestMethod.POST)
    public @ResponseBody String loginAjax(final String username, final String password, final HttpSession session) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Logging in user " + username);
        }

        final String result;

        final boolean outCome = accountService.authenticate(username, password);

        if (outCome) {
            result = null;
            session.setAttribute(CURRENT_PRINCIPAL_TAG, username);

        } else {
            result = AUTHENTICATION_FAILURE_MESSAGE;
        }

        return result;
    }

}
