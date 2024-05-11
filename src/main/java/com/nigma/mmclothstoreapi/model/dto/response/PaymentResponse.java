package com.nigma.mmclothstoreapi.model.dto.response;

import com.nigma.mmclothstoreapi.constant.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PaymentResponse {
    private String orderId;
    private String customerId;
    private LocalDateTime paymentDate;
    private Long nominal;
    private Status status;

}
