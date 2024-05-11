package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.request.ExchangeRequest;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.ExchangeResponse;
import com.nigma.mmclothstoreapi.service.ExchangeRewardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.EXCHANGE)
public class ExchangeRewardController {
    private final ExchangeRewardService exchangeRewardService;

    @PostMapping
    @PreAuthorize("hasAnyRole('CUSTOMER')")
    public ResponseEntity<?> createExchangeRewardRequest(@RequestBody ExchangeRequest request){
        ExchangeResponse response = exchangeRewardService.create(request);
        CommmonResponse<ExchangeResponse> commmonResponse = CommmonResponse.<ExchangeResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Success create request to exchange reward")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ExchangeResponse> responses = exchangeRewardService.getAll();
        CommmonResponse<List<ExchangeResponse>> commmonResponse = CommmonResponse.<List<ExchangeResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all exchange request data")
                .data(responses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        ExchangeResponse response = exchangeRewardService.getById(id);
        CommmonResponse<ExchangeResponse> commmonResponse = CommmonResponse.<ExchangeResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Get exchange reward request")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }
}
