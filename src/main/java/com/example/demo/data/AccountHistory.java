package com.example.demo.data;

public class AccountHistory {

    private String id;

    private Double amount;

    public AccountHistory(String id, Double amount) {
        this.id = id;
        this.amount = amount;
    }

    public AccountHistory(){}

    public String getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setId(String id) {
        this.id = id;
    }
    
}