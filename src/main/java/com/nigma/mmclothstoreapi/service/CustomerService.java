package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;

import java.util.List;

public interface CustomerService {
    Customer create(Customer customer);
    Customer getById(String id);
    List<CustomerResponse> getAll();
    Customer update(Customer customer);
}
