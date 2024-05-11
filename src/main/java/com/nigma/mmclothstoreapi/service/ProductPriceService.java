package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.entity.ProductPrice;

import java.util.List;

public interface ProductPriceService {
    ProductPrice create(ProductPrice productPrice);
    ProductPrice getById(String id);
    ProductPrice update(ProductPrice productPrice);
    List<ProductPrice> getAll();
    ProductPrice getByProductId(String id);
}
