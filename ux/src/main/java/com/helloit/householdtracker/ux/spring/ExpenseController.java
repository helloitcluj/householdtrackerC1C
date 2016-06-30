package com.helloit.householdtracker.ux.spring;

import com.helloit.householdtracker.ux.common.services.IExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.Date;


@Controller
@RequestMapping(path = "expense")
public class ExpenseController {


    @Autowired
    private IExpenseService expenseService;

    @RequestMapping(path = "create", method = RequestMethod.POST)
    public void create(final HttpSession session, final Date date, final double amount, final String description) {

    }
}