package com.wedoogift.backendchallenge.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "user")
@Getter
@NoArgsConstructor
public class User {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @OneToMany(mappedBy="user", cascade = CascadeType.ALL)
    private List<Distribution> distributions = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<UserWallet> userWallets = new ArrayList<>();

    public User(long id, List<UserWallet> userWallets) {
        this.id=id;
        this.userWallets=userWallets;
    }
}
