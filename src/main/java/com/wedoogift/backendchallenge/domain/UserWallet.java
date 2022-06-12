package com.wedoogift.backendchallenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_wallet")
@Getter
@NoArgsConstructor
public class UserWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    @Column(name = "balance")
    private int balance;

    public UserWallet(long id, int distributionAmount) {
        this.id = id;
        this.balance = distributionAmount;
    }

    public UserWallet(long id, int distributionAmount, Wallet wallet, User user) {
        this.id=id;
        this.balance=distributionAmount;
        this.wallet=wallet;
        this.user=user;
    }

    public UserWallet saveCalculateBalanceUserWalletByLevel(int distributionAmount, Wallet wallet, User user) {
           return new UserWallet(this.id,
                   this.balance = distributionAmount,
                   this.wallet = wallet,
                   this.user = user);
    }
    public UserWallet updateCalculateBalanceUserWalletByLevel(int distributionAmount, Wallet wallet, User user) {
        return new UserWallet(this.id,
                this.balance += distributionAmount,
                this.wallet = wallet,
                this.user = user);
    }
}
