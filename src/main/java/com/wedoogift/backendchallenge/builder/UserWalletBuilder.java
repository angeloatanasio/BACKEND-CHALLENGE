package com.wedoogift.backendchallenge.builder;

import com.wedoogift.backendchallenge.domain.User;
import com.wedoogift.backendchallenge.domain.UserWallet;
import com.wedoogift.backendchallenge.domain.Wallet;

public class UserWalletBuilder {

    private long id;
    private User user;
    private Wallet wallet;
    private int balance;

    public UserWalletBuilder withId(long id) {
        this.id=id;
        return this;
    }
    public UserWalletBuilder withUser(User user) {
        this.user=user;
        return this;
    }
    public UserWalletBuilder withWallet(Wallet wallet) {
        this.wallet=wallet;
        return this;
    }
    public UserWalletBuilder withBalance(int balance) {
        this.balance=balance;
        return this;
    }

    public UserWallet build() {
        return new UserWallet(id, user, wallet, balance);
    }
}
