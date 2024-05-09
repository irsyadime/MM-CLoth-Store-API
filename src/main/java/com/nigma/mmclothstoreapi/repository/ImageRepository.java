package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image,String> {
}
