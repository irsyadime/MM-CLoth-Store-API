package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.request.OrderRequest;
import com.nigma.mmclothstoreapi.model.dto.response.OrderResponse;
import com.nigma.mmclothstoreapi.model.dto.response.PaymentResponse;

public interface OrderService {
    OrderResponse create(OrderRequest request);
    OrderResponse getById(String id);
    OrderResponse getAll();
    PaymentResponse payOrder(String id);
}
