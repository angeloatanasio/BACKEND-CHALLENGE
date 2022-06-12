package com.wedoogift.backendchallenge.domain;

import com.wedoogift.backendchallenge.shared.LevelEnum;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "company")
@Getter
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "balance")
    private int balance;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Distribution> distributions = new ArrayList<>();

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<User> users = new ArrayList<>();

    public Company(long id, int distributionAmount, String name) {
        this.id=id;
        this.balance=distributionAmount;
        this.name=name;
    }

    public Company calculateBalanceCompanyByLevel( int distributionAmount, String name) {
          return new Company(this.id,
                  this.balance-= distributionAmount,
                  this.name = name);
    }
}
