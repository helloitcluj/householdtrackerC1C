package com.helloit.householdtracker.ux.common.services;

import com.helloit.householdtracker.ux.common.entities.Expense;

/**
 */

public interface IExpenseService {

    Expense save(String date, double amount, String description, Integer userId);
}
