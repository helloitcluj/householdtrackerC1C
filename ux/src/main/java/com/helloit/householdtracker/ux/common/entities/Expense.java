package com.helloit.householdtracker.ux.common.entities;


import javax.persistence.*;
import java.util.Calendar;


@Entity
@Table(name = "Expense")
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private  double amount ;

    @Temporal(TemporalType.TIMESTAMP)
    private Calendar date ;


    private String description;

    public Expense(double amount, Calendar date, String description, Integer accountId) {
        this.amount = amount;
        this.date = date;
        this.description = description;
        this.accountId = accountId;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    private  Integer accountId ;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
