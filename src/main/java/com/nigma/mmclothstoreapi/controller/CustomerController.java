package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;
import com.nigma.mmclothstoreapi.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.CUSTOMER)
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(@RequestBody Customer request){
        Customer customer = customerService.create(request);
        CommmonResponse<Customer> commmonResponse = CommmonResponse.<Customer>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(customer)
                .message("Create customer success")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<CustomerResponse> responses = customerService.getAll();
        CommmonResponse<List<CustomerResponse>> commmonResponse = CommmonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .data(responses)
                .message("Get all customer")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Customer customer = customerService.getById(id);
        CommmonResponse<Customer> commmonResponse = CommmonResponse.<Customer>builder()
                .statusCode(HttpStatus.OK.value())
                .data(customer)
                .message("Get customer data")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
}
