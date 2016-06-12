package com.helloit.householdtracker.ux.common.services;

/**
 */

public interface IAccountService {
    enum CreationOutComes {
        SUCCESS,
        PASSWORD_DID_NOT_MATCH,
        EXISTING_ACCOUNT
    }

    CreationOutComes createAccount(String username, String password, String retypedPassword);
}
