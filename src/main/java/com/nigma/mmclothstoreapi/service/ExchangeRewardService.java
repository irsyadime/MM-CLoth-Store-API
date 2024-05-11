package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.request.ExchangeRequest;
import com.nigma.mmclothstoreapi.model.dto.response.ExchangeResponse;

import java.util.List;

public interface ExchangeRewardService {
    ExchangeResponse create(ExchangeRequest request);
    ExchangeResponse getById(String id);
    List<ExchangeResponse> getAll();
}
