package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.common.repository.IUserRepository;
import com.helloit.householdtracker.ux.common.services.IAccountService;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 */

@Service
public class AccountService implements IAccountService {

    @Resource
    private IUserRepository userRepository;


    @Override
    public boolean authenticate(final String username,final String password) {
        final User user = userRepository.findOneByUsername(username);

        return user != null && password.equals(user.getPassword());
    }

    @Override
    public User find(String username) {

        return userRepository.findOneByUsername(username);
    }

    @Override
    public CreationOutComes createAccount(final String username, final String password, final String retypedPassword) {
        CreationOutComes result;

        if (password.equals(retypedPassword)) {
            final User entity = new User();
            entity.setUsername(username);
            entity.setPassword(password);

            try {
                userRepository.save(entity);
                result = CreationOutComes.SUCCESS;
            } catch (final JpaSystemException ex) {
                boolean found = false;
                for (Throwable exceptionChain = ex; exceptionChain != null && !found; exceptionChain = exceptionChain.getCause()) {
                    found = exceptionChain instanceof ConstraintViolationException;
                }

                if (found) {
                    result = CreationOutComes.EXISTING_ACCOUNT;
                } else {
                    throw ex;
                }

            }

        } else {
            result = CreationOutComes.PASSWORD_DID_NOT_MATCH;
        }

        return result;
    }

}
