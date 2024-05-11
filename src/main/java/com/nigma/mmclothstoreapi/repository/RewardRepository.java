package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.Reward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RewardRepository extends JpaRepository<Reward,String> {
}
