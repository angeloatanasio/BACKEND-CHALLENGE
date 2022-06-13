package com.wedoogift.backendchallenge.builder;

import com.wedoogift.backendchallenge.domain.Distribution;
import com.wedoogift.backendchallenge.domain.UserWallet;
import com.wedoogift.backendchallenge.domain.Wallet;

import java.util.ArrayList;
import java.util.List;

public class WalletBuilder {

    private long id;
    private String name;
    private String type;
    private List<Distribution> distributions = new ArrayList<>();
    private List<UserWallet> userWallets = new ArrayList<>();

    public WalletBuilder withId(long id) {
        this.id=id;
        return this;
    }
    public WalletBuilder withName(String name) {
        this.name=name;
        return this;
    }
    public WalletBuilder withType(String type) {
        this.type=type;
        return this;
    }
    public WalletBuilder withDistributions(List<Distribution> distributions) {
        this.distributions=distributions;
        return this;
    }
    public WalletBuilder withUserWallets(List<UserWallet> userWallets) {
        this.userWallets=userWallets;
        return this;
    }

    public Wallet build() {
        return new Wallet(id, name, type, distributions, userWallets);
    }

}
