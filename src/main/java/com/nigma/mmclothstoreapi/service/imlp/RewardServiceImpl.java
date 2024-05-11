package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.dto.response.RewardResponse;
import com.nigma.mmclothstoreapi.model.entity.Reward;
import com.nigma.mmclothstoreapi.model.mapper.RewardMapper;
import com.nigma.mmclothstoreapi.repository.RewardRepository;
import com.nigma.mmclothstoreapi.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RewardServiceImpl implements RewardService {
    private final RewardRepository rewardRepository;

    @Override
    public Reward getById(String id) {
        return rewardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reward Not Found"));
    }

    @Override
    public List<RewardResponse> getAll() {
        List<Reward> rewards = rewardRepository.findAll();
        List<RewardResponse> responses = new ArrayList<>();
        for(Reward reward : rewards){
            responses.add(RewardMapper.toDto(reward));
        }
        return responses;
    }
}
