package com.nigma.mmclothstoreapi.model.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductRequest {
    private String id;
    private String merchantId;
    private String name;
    private Long price;
    private Integer stock;
}
