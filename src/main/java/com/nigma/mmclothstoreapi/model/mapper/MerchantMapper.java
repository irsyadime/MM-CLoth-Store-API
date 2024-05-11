package com.nigma.mmclothstoreapi.model.mapper;

import com.nigma.mmclothstoreapi.model.dto.response.MerchantResponse;
import com.nigma.mmclothstoreapi.model.entity.Merchant;
import org.springframework.stereotype.Component;

@Component
public class MerchantMapper {
    public static MerchantResponse toDto(Merchant merchant){
        return MerchantResponse.builder()
                .id(merchant.getId())
                .name(merchant.getName())
                .phone(merchant.getPhone())
                .build();
    }
    public static Merchant toEntity(MerchantResponse merchantResponse){
        return Merchant.builder()
                .id(merchantResponse.getId())
                .name(merchantResponse.getName())
                .phone(merchantResponse.getPhone())
                .build();
    }
}
