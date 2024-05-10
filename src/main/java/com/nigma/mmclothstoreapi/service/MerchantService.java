package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.entity.Merchant;

import java.util.List;

public interface MerchantService {
    Merchant create(Merchant merchant);
    Merchant getById(String id);
    List<Merchant> getAll();
}
