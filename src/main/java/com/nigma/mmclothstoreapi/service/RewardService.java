package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.response.RewardResponse;
import com.nigma.mmclothstoreapi.model.entity.Reward;

import java.util.List;

public interface RewardService {
    Reward getById(String id);
    List<RewardResponse> getAll();
}
