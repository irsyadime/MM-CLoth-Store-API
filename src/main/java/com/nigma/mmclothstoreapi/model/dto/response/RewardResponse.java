package com.nigma.mmclothstoreapi.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class RewardResponse {
    private String id;
    private String rewardName;
    private Integer pointCost;
}
