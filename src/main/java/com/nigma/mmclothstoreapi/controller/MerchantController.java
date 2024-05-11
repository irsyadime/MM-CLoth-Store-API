package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.dto.response.MerchantResponse;
import com.nigma.mmclothstoreapi.model.entity.Merchant;
import com.nigma.mmclothstoreapi.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.MERCHANT)
public class MerchantController {
    private final MerchantService merchantService;

    @PostMapping
    public ResponseEntity<?> createMerchant(@RequestBody Merchant request){
        Merchant merchant = merchantService.create(request);
        CommmonResponse<Merchant> commmonResponse = CommmonResponse.<Merchant>builder()
                .statusCode(HttpStatus.CREATED.value())
                .data(merchant)
                .message("Create merchant success")
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }
    @GetMapping
    public ResponseEntity<?> getAll(){
        List<MerchantResponse> merchants = merchantService.getAll();
        CommmonResponse<List<MerchantResponse>> commmonResponse = CommmonResponse.<List<MerchantResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .data(merchants)
                .message("Get all merchant")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        Merchant merchant = merchantService.getById(id);
        CommmonResponse<Merchant> commmonResponse = CommmonResponse.<Merchant>builder()
                .statusCode(HttpStatus.OK.value())
                .data(merchant)
                .message("Get merchant data")
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getAllCustomerBoughtProduct(@PathVariable String id){
        List<CustomerResponse> responses = merchantService.getAllCustomerBoughtProduct(id);
        CommmonResponse<List<CustomerResponse>> commmonResponse = CommmonResponse.<List<CustomerResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get All user who bought this product")
                .data(responses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
}
