package com.wedoogift.backendchallenge.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "distribution")
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Distribution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "amount")
    private int amount;

    @Column(name = "start_date")
    private String startDate;

    @Column(name = "end_date")
    private String endDate;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "wallet_id")
    private Wallet wallet;

    public Distribution(int distributionAmount, Wallet wallet) {
        this.amount = distributionAmount;
        this.wallet = wallet;
    }

    public Distribution(long id, int distributionAmount) {
        this.id =id;
        this.amount=distributionAmount;
    }

    public Distribution saveCalculateDistribution(int distributionAmount,
                                                  String dateOfDistribution,
                                                  String lifeSpanDistribution,
                                                  Company company,
                                                  User user,
                                                  Wallet wallet) {
        return new Distribution(this.id,
                distributionAmount,
                dateOfDistribution,
                lifeSpanDistribution,
                company,
                user,
                wallet);
    }

    public Distribution updateCalculateDistribution(int distributionAmount,
                                                    String dateOfDistribution,
                                                    String lifeSpanDistribution,
                                                    Company company,
                                                    User user,
                                                    Wallet wallet) {
        return new Distribution(this.id,
                this.amount += distributionAmount,
                dateOfDistribution,
                lifeSpanDistribution,
                company,
                user,
                wallet);
    }

    public Distribution calculateDistributionWhenitExpire(int distributionRegistered,
                                                          String dateOfDistribution,
                                                          String lifeSpanDistribution,
                                                          Company company,
                                                          User user,
                                                          Wallet wallet){
        return new Distribution(this.id,
                this.amount-=distributionRegistered,
                dateOfDistribution,
                lifeSpanDistribution,
                company,
                user,
                wallet);
    }
}
