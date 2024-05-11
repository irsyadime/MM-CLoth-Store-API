package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.request.OrderRequest;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.OrderResponse;
import com.nigma.mmclothstoreapi.model.dto.response.PaymentResponse;
import com.nigma.mmclothstoreapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.ORDER)
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request){
        OrderResponse orderResponse = orderService.create(request);
        CommmonResponse<OrderResponse> commmonResponse = CommmonResponse.<OrderResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Order created")
                .data(orderResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        OrderResponse orderResponse = orderService.getById(id);
        CommmonResponse<OrderResponse> commmonResponse = CommmonResponse.<OrderResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get order by id")
                .data(orderResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<?> payOrder(@PathVariable String id){
        PaymentResponse response = orderService.payOrder(id);
        CommmonResponse<PaymentResponse> commmonResponse = CommmonResponse.<PaymentResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Payment success")
                .data(response)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }
}
