package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.constant.ERole;
import com.nigma.mmclothstoreapi.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,String> {
    Optional<Role> findByName(ERole name);
}
