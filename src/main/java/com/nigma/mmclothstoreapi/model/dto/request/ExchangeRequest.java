package com.nigma.mmclothstoreapi.model.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExchangeRequest {
    private String customerId;
    private String rewardId;
}
