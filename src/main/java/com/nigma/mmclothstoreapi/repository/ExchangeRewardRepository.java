package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.ExchangeReward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRewardRepository extends JpaRepository<ExchangeReward,String> {
}
