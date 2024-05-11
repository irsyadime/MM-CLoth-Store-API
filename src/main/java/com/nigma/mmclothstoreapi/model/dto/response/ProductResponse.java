package com.nigma.mmclothstoreapi.model.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class ProductResponse {
    private String id;
    private String name;
    private Long price;
    private Integer stock;
    private MerchantResponse merchant;
    private Boolean isActive;
}
