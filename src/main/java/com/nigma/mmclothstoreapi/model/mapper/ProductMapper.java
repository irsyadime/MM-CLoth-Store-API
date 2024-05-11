package com.nigma.mmclothstoreapi.model.mapper;

import com.nigma.mmclothstoreapi.model.dto.response.ProductResponse;
import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {
    public static ProductResponse convertFromProductPrice(ProductPrice productPrice){
        return ProductResponse.builder()
                .id(productPrice.getProduct().getId())
                .name(productPrice.getProduct().getName())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .merchant(MerchantMapper.toDto(productPrice.getMerchant()))
                .isActive(productPrice.getIsActive())
                .build();
    }
}
