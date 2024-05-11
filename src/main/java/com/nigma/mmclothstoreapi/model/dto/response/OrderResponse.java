package com.nigma.mmclothstoreapi.model.dto.response;

import com.nigma.mmclothstoreapi.constant.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderResponse {
    private String id;
    private LocalDateTime transDate;
    private CustomerResponse customer;
    private ProductResponse product;
    private Integer qty;
    private Status status;
}
