package com.nigma.mmclothstoreapi.model.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ExchangeResponse {
    private String id;
    private LocalDateTime exchangeDate;
    private CustomerResponse customer;
    private RewardResponse reward;
}
