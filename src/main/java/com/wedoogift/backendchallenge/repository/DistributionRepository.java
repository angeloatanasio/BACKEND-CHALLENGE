package com.wedoogift.backendchallenge.repository;

import com.wedoogift.backendchallenge.domain.Distribution;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistributionRepository extends JpaRepository<Distribution, Long> {

    @Query("select d from Distribution d where d.wallet.id=1")
    List<Distribution> findDistributionByLevel1();
}
