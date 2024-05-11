package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.dto.response.MerchantResponse;
import com.nigma.mmclothstoreapi.model.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant create(Merchant merchant);
    Merchant getById(String id);
    List<MerchantResponse> getAll();
    List<CustomerResponse> getAllCustomerBoughtProduct(String id);
}
