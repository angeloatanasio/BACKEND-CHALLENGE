package com.wedoogift.backendchallenge.builder;

import com.wedoogift.backendchallenge.domain.Company;
import com.wedoogift.backendchallenge.domain.Distribution;
import com.wedoogift.backendchallenge.domain.User;
import com.wedoogift.backendchallenge.domain.Wallet;

public class DistributionBuilder {

    private long id;
    private int amount;
    private String startDate;
    private String endDate;
    private Company company;
    private User user;
    private Wallet wallet;

    public DistributionBuilder withId(long id) {
        this.id=id;
        return this;
    }
    public DistributionBuilder withAmount(int amount) {
        this.amount=amount;
        return this;
    }
    public DistributionBuilder withStartDate(String startDate) {
        this.startDate=startDate;
        return this;
    }
    public DistributionBuilder withEndDate(String endDate) {
        this.endDate=endDate;
        return this;
    }
    public DistributionBuilder withCompany(Company company) {
        this.company=company;
        return this;
    }
    public DistributionBuilder withUser(User user) {
        this.user=user;
        return this;
    }
    public DistributionBuilder withWallet(Wallet wallet) {
        this.wallet=wallet;
        return this;
    }

    public Distribution build() {
        return new Distribution(id, amount, startDate, endDate, company, user, wallet);
    }
}
