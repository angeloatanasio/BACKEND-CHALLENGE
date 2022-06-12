package com.wedoogift.backendchallenge.repository;

import com.wedoogift.backendchallenge.domain.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> getWalletByType(@Param("type") String type);
}
