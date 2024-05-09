package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.Merchant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MerchantRepository extends JpaRepository<Merchant,String> {
}
