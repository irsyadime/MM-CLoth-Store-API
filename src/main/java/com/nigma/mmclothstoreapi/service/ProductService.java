package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.request.ProductRequest;
import com.nigma.mmclothstoreapi.model.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse createProductAndPrice(ProductRequest request);
    ProductResponse getById(String id);
    List<ProductResponse> getAll();
    ProductResponse update(ProductRequest request);
    void deletePrice(String id);
    void delete(String id);
}
