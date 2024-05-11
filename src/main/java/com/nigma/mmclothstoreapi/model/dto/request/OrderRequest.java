package com.nigma.mmclothstoreapi.model.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class OrderRequest {
    private String customerId;
    private String productPriceId;
    private Integer qty;
}
