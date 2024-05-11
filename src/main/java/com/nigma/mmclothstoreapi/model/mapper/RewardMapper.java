package com.nigma.mmclothstoreapi.model.mapper;

import com.nigma.mmclothstoreapi.model.dto.response.RewardResponse;
import com.nigma.mmclothstoreapi.model.entity.Reward;
import org.springframework.stereotype.Component;

@Component
public class RewardMapper {
    public static RewardResponse toDto(Reward reward){
        return RewardResponse.builder()
                .id(reward.getId())
                .rewardName(reward.getRewardName())
                .pointCost(reward.getPointCost())
                .build();
    }

    public static Reward toEntity(RewardResponse response){
        return Reward.builder()
                .id(response.getId())
                .rewardName(response.getRewardName())
                .pointCost(response.getPointCost())
                .build();
    }
}
