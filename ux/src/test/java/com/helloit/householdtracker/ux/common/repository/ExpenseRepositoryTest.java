package com.helloit.householdtracker.ux.common.repository;


import com.helloit.householdtracker.ux.common.entities.Expense;
import com.helloit.householdtracker.ux.common.entities.User;
import com.helloit.householdtracker.ux.spring.AppConfig;
import com.helloit.householdtracker.ux.spring.WebConfig;
import com.helloit.householdtracker.ux.tools.SchemaManager;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {AppConfig.class, WebConfig.class})
@WebAppConfiguration
public class ExpenseRepositoryTest {

    @Autowired
    private IUserRepository userRepository;


    @Autowired
    private IExpenseRepository expenseRepository;
    private User testUser;


    @Before
    public void setup () {
        final SchemaManager schemaManager = new SchemaManager();
        schemaManager.recreateSchema();

        final User user = new User();
        user.setUsername("Test");
        user.setPassword("1");

        testUser = userRepository.save(user);



    }

    @Test
    public void basicTest() {
        Calendar now = Calendar.getInstance();
        final Expense expense = new Expense(32.5, now, "Chocolate", testUser.getId());

        final Expense save = expenseRepository.save(expense);

        Assert.assertEquals("Should have oa id of 0", new Integer(0), save.getId());

    }

    @Test
    public void foreingKeyTest() {
        Calendar now = Calendar.getInstance();
        final Expense expense = new Expense(32.5, now, "Chocolate",testUser.getId() - 1);

        try {
            Expense save = expenseRepository.save(expense);
            throw new UnsupportedOperationException("Should not happen!");
        } catch (RuntimeException ex) {
            //should fail
        }

    }
}
