package com.helloit.householdtracker.ux.common.services;

import com.helloit.householdtracker.ux.common.entities.User;

/**
 */

public interface IAccountService {
    boolean authenticate(String username, String password);

    User find(String username);

    enum CreationOutComes {
        SUCCESS,
        PASSWORD_DID_NOT_MATCH,
        EXISTING_ACCOUNT
    }

    CreationOutComes createAccount(String username, String password, String retypedPassword);
}
