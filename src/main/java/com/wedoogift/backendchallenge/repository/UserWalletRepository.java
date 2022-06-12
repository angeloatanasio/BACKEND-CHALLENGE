package com.wedoogift.backendchallenge.repository;

import com.wedoogift.backendchallenge.domain.UserWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserWalletRepository extends JpaRepository<UserWallet, Long> { }
