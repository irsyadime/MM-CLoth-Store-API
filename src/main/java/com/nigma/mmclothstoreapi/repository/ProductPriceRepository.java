package com.nigma.mmclothstoreapi.repository;

import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductPriceRepository extends JpaRepository<ProductPrice,String> {
    ProductPrice findByProduct_Id(String productId);
}
