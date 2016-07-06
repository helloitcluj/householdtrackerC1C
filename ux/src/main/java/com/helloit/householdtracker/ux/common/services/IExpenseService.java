package com.helloit.householdtracker.ux.common.services;

import com.helloit.householdtracker.ux.common.entities.Expense;

import java.util.List;

/**
 */

public interface IExpenseService {

    Expense save(String date, double amount, String description, Integer userId);

    List<Expense> findAllByAccountId(Integer id);
}
