package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer,String> {
}
