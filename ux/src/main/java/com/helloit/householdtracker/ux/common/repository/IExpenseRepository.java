package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IExpenseRepository extends JpaRepository<Expense, Integer> {


}
