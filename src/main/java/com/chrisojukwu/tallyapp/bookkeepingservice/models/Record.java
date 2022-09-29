package com.chrisojukwu.tallyapp.bookkeepingservice.models;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity(name = "records_table") //instructs Spring to create database table
public class Record {

    @Id  //tells the database that this is the Id field
    @GeneratedValue
    private Integer id;

    private Boolean isIncome;

    private LocalDate date;

    private BigDecimal amount;

    @Size(min = 3)
    private String description;

    private Integer userId; //this identifies the owner of the record

    public Record(Integer id, Boolean isIncome, LocalDate date, BigDecimal amount, String description, Integer userId) {
        this.id = id;
        this.isIncome = isIncome;
        this.date = date;
        this.amount = amount;
        this.description = description;
        this.userId = userId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getIncome() {
        return isIncome;
    }

    public void setIncome(Boolean income) {
        isIncome = income;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
