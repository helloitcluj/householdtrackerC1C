package com.helloit.householdtracker.ux.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;


@Service
@Controller
@RequestMapping("register")
public class RegisterController {

    private static final Logger LOGGER = LogManager.getLogger(RegisterController.class);

    private static final String REGISTER = "register";

    /*@Resource
    private IUserRepository userRepository;*/


    @Transactional
    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(final ModelMap model) {
        LOGGER.info("welcome!");

		/*final new_user entity = new User();
        entity.setName("test");
        entity.setEmplNumber(1);
        final new_user savedEntity = IUserRepository.save(entity);*/

        model.addAttribute("message", new Date());

        return REGISTER;
    }
}