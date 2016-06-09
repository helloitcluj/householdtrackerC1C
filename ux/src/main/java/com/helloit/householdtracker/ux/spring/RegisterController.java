package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;


@Controller
public class RegisterController {

    public static final String MISMATCHED_PASSWORD_MESSAGE = "The passwords you have provided doesn't match, please retype it!";
    public static final String ERROR = "error";
    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
    private static final String REGISTER = "register";
    @Resource
    private IUserRepository userRepository;

    private void showMessageDialog(Object o, String s) {
    }

    @Transactional
    @RequestMapping(path = "register", method = RequestMethod.POST)
    public String register(final String name, final String password, final String retypedPassword, final ModelMap model) {
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("Registering user " + name);
        }

        final String result;

        if (password.equals(retypedPassword)) {
            final User entity = new User();
            entity.setName(name);
            entity.setPassword(password);

            userRepository.save(entity);

            result = REGISTER;
        } else {
            model.addAttribute("message", MISMATCHED_PASSWORD_MESSAGE);

            result = ERROR;

        }

        return result;
    }
}
