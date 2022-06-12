package com.wedoogift.backendchallenge.repository;

import com.wedoogift.backendchallenge.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from User u " +
            "inner join UserWallet  uw on u.id = uw.user.id " +
            "inner join Wallet w on w.id = uw.wallet.id " +
            "where w.id =1 and uw.wallet.id=1")
    List<User> findUsersByLevel1();
}
