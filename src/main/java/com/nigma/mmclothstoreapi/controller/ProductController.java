package com.nigma.mmclothstoreapi.controller;

import com.nigma.mmclothstoreapi.constant.Route;
import com.nigma.mmclothstoreapi.model.dto.request.ProductRequest;
import com.nigma.mmclothstoreapi.model.dto.response.CommmonResponse;
import com.nigma.mmclothstoreapi.model.dto.response.ProductResponse;
import com.nigma.mmclothstoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping(Route.PRODUCT)
public class ProductController {
    private final ProductService productService;

    @PostMapping
    @PreAuthorize("hasAnyRole('MERCHANT')")
    public ResponseEntity<?> createProductAndPrice(@RequestBody ProductRequest request){
        ProductResponse productResponse = productService.createProductAndPrice(request);
        CommmonResponse<ProductResponse> commmonResponse = CommmonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message("Product created")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(commmonResponse);
    }

    @GetMapping
    public ResponseEntity<?> getAll(){
        List<ProductResponse> productResponses = productService.getAll();
        CommmonResponse<List<ProductResponse>> commmonResponse = CommmonResponse.<List<ProductResponse>>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Get all product")
                .data(productResponses)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        ProductResponse productResponse = productService.getById(id);
        CommmonResponse<ProductResponse> commmonResponse = CommmonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product found")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @PutMapping
    @PreAuthorize("hasAnyRole('MERCHANT')")
    public ResponseEntity<?> update(@RequestBody ProductRequest request){
        ProductResponse productResponse = productService.update(request);
        CommmonResponse<ProductResponse> commmonResponse = CommmonResponse.<ProductResponse>builder()
                .statusCode(HttpStatus.OK.value())
                .message("Product updated")
                .data(productResponse)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(commmonResponse);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAnyRole('MERCHANT')")
    public ResponseEntity<?> delete(@PathVariable String id){
        productService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/price/{id}")
    @PreAuthorize("hasAnyRole('MERCHANT')")
    public ResponseEntity<?> deletePrice(@PathVariable String id){
        productService.deletePrice(id);
        return ResponseEntity.noContent().build();
    }
}
