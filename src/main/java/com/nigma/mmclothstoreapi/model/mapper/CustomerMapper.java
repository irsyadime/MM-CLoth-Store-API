package com.nigma.mmclothstoreapi.model.mapper;

import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.dto.response.MerchantResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;
import com.nigma.mmclothstoreapi.model.entity.Merchant;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    public static CustomerResponse toDto(Customer customer){
        return CustomerResponse.builder()
                .id(customer.getId())
                .name(customer.getName())
                .phone(customer.getPhone())
                .point(customer.getPoint())
                .build();
    }
    public static Customer toEntity(CustomerResponse customerResponse){
        return Customer.builder()
                .id(customerResponse.getId())
                .name(customerResponse.getName())
                .phone(customerResponse.getPhone())
                .point(customerResponse.getPoint())
                .build();
    }
}
