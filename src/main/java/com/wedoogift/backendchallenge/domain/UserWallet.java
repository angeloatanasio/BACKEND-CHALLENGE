package com.wedoogift.backendchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "user_wallet")
@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    public UserWallet saveCalculateBalanceUserWalletByLevel( User user, Wallet wallet,int distributionAmount) {
           return new UserWallet(this.id,
                   this.user = user,
                   this.wallet = wallet,
                   this.balance = distributionAmount);
    }
    public UserWallet updateCalculateBalanceUserWalletByLevel(User user, Wallet wallet,int distributionAmount ) {
        return new UserWallet(this.id,
                this.user = user,
                this.wallet = wallet,
                this.balance += distributionAmount);
    }
}
