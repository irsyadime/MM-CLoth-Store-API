package com.nigma.mmclothstoreapi.model.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class MerchantResponse {
    private String id;
    private String name;
    private String phone;
}
