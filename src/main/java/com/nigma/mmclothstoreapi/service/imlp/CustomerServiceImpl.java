package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;
import com.nigma.mmclothstoreapi.repository.CustomerRepository;
import com.nigma.mmclothstoreapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    @Override
    public Customer create(Customer customer) {
        Customer newCustomer = Customer.builder()
                .name(customer.getName())
                .phone(customer.getPhone())
                .point(0)
                .userCredential(customer.getUserCredential())
                .build();
        return customerRepository.save(newCustomer);
    }

    @Override
    public List<CustomerResponse> getAll() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerResponse> customerResponses = new ArrayList<>();
        for(Customer customer : customers){
            customerResponses.add(
                    CustomerResponse.builder()
                            .id(customer.getId())
                            .name(customer.getName())
                            .phone(customer.getPhone())
                            .point(customer.getPoint())
                            .build()
            );
        }
        return customerResponses;
    }

    @Override
    public Customer getById(String id) {
        return customerRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));
    }

    @Override
    public Customer update(Customer customer) {
        Customer existing = customerRepository.findById(customer.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Customer Not Found"));
        existing.setId(customer.getId());
        return customerRepository.saveAndFlush(existing);
    }
}
