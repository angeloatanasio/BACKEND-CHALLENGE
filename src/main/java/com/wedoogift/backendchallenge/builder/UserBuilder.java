package com.wedoogift.backendchallenge.builder;

import com.wedoogift.backendchallenge.domain.Company;
import com.wedoogift.backendchallenge.domain.Distribution;
import com.wedoogift.backendchallenge.domain.User;
import com.wedoogift.backendchallenge.domain.UserWallet;

import java.util.ArrayList;
import java.util.List;


public class UserBuilder {

    private long id;
    private List<Distribution> distributions = new ArrayList<>();
    private Company company;
    private List<UserWallet> userWallets = new ArrayList<>();

    public UserBuilder withId(long id) {
        this.id=id;
        return this;
    }

    public UserBuilder withDistributions(List<Distribution> distributions) {
        this.distributions = distributions;
        return this;
    }

    public UserBuilder withCompany(Company company) {
        this.company=company;
        return this;
    }

    public UserBuilder withUserWallets(List<UserWallet> userWallets) {
        this.userWallets=userWallets;
        return this;
    }

    public User buuild() {
        return new User(id, distributions, company, userWallets);
    }
}
