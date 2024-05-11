package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.dto.request.ExchangeRequest;
import com.nigma.mmclothstoreapi.model.dto.response.ExchangeResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;
import com.nigma.mmclothstoreapi.model.entity.ExchangeReward;
import com.nigma.mmclothstoreapi.model.entity.Reward;
import com.nigma.mmclothstoreapi.model.mapper.CustomerMapper;
import com.nigma.mmclothstoreapi.model.mapper.RewardMapper;
import com.nigma.mmclothstoreapi.repository.ExchangeRewardRepository;
import com.nigma.mmclothstoreapi.service.CustomerService;
import com.nigma.mmclothstoreapi.service.ExchangeRewardService;
import com.nigma.mmclothstoreapi.service.RewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExchangeRewardServiceImpl implements ExchangeRewardService {
    private final ExchangeRewardRepository exchangeRewardRepository;
    private final CustomerService customerService;
    private final RewardService rewardService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ExchangeResponse create(ExchangeRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        Reward reward = rewardService.getById(request.getRewardId());
        if(customer.getPoint() >= reward.getPointCost()){
            customer.setPoint(customer.getPoint() - reward.getPointCost());
            ExchangeReward exchangeReward = ExchangeReward.builder()
                    .exchangeDate(LocalDateTime.now())
                    .customer(customer)
                    .reward(reward)
                    .build();
            exchangeRewardRepository.saveAndFlush(exchangeReward);
            customerService.update(customer);

            return ExchangeResponse.builder()
                    .id(exchangeReward.getId())
                    .exchangeDate(exchangeReward.getExchangeDate())
                    .customer(CustomerMapper.toDto(customer))
                    .reward(RewardMapper.toDto(reward))
                    .build();
        }
        return null;
    }

    @Override
    public ExchangeResponse getById(String id) {
        ExchangeReward exchangeReward = exchangeRewardRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Exchange reward Not Found"));

        return ExchangeResponse.builder()
                .id(exchangeReward.getId())
                .exchangeDate(exchangeReward.getExchangeDate())
                .customer(CustomerMapper.toDto(exchangeReward.getCustomer()))
                .reward(RewardMapper.toDto(exchangeReward.getReward()))
                .build();
    }

    @Override
    public List<ExchangeResponse> getAll() {
        List<ExchangeReward> exchangeRewardList = exchangeRewardRepository.findAll();
        List<ExchangeResponse> exchangeResponseList = new ArrayList<>();
        for(ExchangeReward exchangeReward : exchangeRewardList){
            exchangeResponseList.add(
                    ExchangeResponse.builder()
                            .id(exchangeReward.getId())
                            .exchangeDate(exchangeReward.getExchangeDate())
                            .customer(CustomerMapper.toDto(exchangeReward.getCustomer()))
                            .reward(RewardMapper.toDto(exchangeReward.getReward()))
                            .build()
            );
        }
        return exchangeResponseList;
    }
}
