package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


@Service
@Controller
@RequestMapping("register")
public class RegisterController {

    public static final String WARNING_MESSAGE = "The passwords you have provided doesn't match, please retype it!";
    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);
    private static final String REGISTER = "register";

    @Resource
    private IUserRepository userRepository;

    private void showMessageDialog(Object o, String s) {
    }

    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(String name, String password, @RequestParam(name = "retypedPassword") String rePassword, final ModelMap model) {
        LOGGER.info("welcome!");

        final User entity = new User();
        entity.setName(name);
        entity.setPassword(password);

        if (password.equals(rePassword)) {
            final User savedEntity = userRepository.save(entity);
        } else {
            model.addAttribute("message", WARNING_MESSAGE);
            //showMessageDialog (null, "The typed passwords doesn't match, please correct them!");

        }


        return REGISTER;
    }
}
