package com.wedoogift.backendchallenge.builder;

import com.wedoogift.backendchallenge.domain.Company;
import com.wedoogift.backendchallenge.domain.Distribution;
import com.wedoogift.backendchallenge.domain.User;

import java.util.ArrayList;
import java.util.List;

public class CompanyBuilder {

    private long id;
    private String name;
    private int balance;
    private List<Distribution> distributions = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public CompanyBuilder withId(long id) {
        this.id = id;
        return this;
    }
    public CompanyBuilder withName(String name) {
        this.name = name;
        return this;
    }
    public CompanyBuilder withBalance(int balance) {
        this.balance = balance;
        return this;
    }
    public CompanyBuilder withDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
        return this;
    }
    public CompanyBuilder withUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Company build() {
        return new Company(id, name, balance, distributions, users);
    }
}
