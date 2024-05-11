package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.constant.Status;
import com.nigma.mmclothstoreapi.model.dto.request.OrderRequest;
import com.nigma.mmclothstoreapi.model.dto.response.OrderResponse;
import com.nigma.mmclothstoreapi.model.dto.response.PaymentResponse;
import com.nigma.mmclothstoreapi.model.entity.Customer;
import com.nigma.mmclothstoreapi.model.entity.Order;
import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import com.nigma.mmclothstoreapi.model.mapper.CustomerMapper;
import com.nigma.mmclothstoreapi.model.mapper.ProductMapper;
import com.nigma.mmclothstoreapi.repository.OrderRepository;
import com.nigma.mmclothstoreapi.service.CustomerService;
import com.nigma.mmclothstoreapi.service.OrderService;
import com.nigma.mmclothstoreapi.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductPriceService productPriceService;
    private final CustomerService customerService;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public OrderResponse create(OrderRequest request) {
        Customer customer = customerService.getById(request.getCustomerId());
        ProductPrice productPrice = productPriceService.getById(request.getProductPriceId());
        Order order = Order.builder()
                .transDate(LocalDateTime.now())
                .customer(customer)
                .productPrice(productPrice)
                .qty(request.getQty())
                .status(Status.UNPAID)
                .build();
        orderRepository.saveAndFlush(order);
        return OrderResponse.builder()
                .id(order.getId())
                .transDate(order.getTransDate())
                .customer(CustomerMapper.toDto(customer))
                .product(ProductMapper.convertFromProductPrice(productPrice))
                .qty(order.getQty())
                .status(order.getStatus())
                .build();
    }

    @Override
    public OrderResponse getById(String id) {
       Order order =  orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found"));;
       return OrderResponse.builder()
               .id(order.getId())
               .transDate(order.getTransDate())
               .customer(CustomerMapper.toDto(order.getCustomer()))
               .product(ProductMapper.convertFromProductPrice(order.getProductPrice()))
               .qty(order.getQty())
               .status(order.getStatus())
               .build();
    }

    @Override
    public OrderResponse getAll() {
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public PaymentResponse payOrder(String id) {
        Order order =  orderRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order Not Found"));
        Customer customer = customerService.getById(order.getCustomer().getId());
        ProductPrice productPrice = productPriceService.getById(order.getProductPrice().getId());
        productPrice.setStock(productPrice.getStock() - order.getQty());
        productPriceService.update(productPrice);
        order.setStatus(Status.PAID);
        orderRepository.saveAndFlush(order);
        customer.setPoint(customer.getPoint()+10);
        customerService.update(customer);

        return PaymentResponse.builder()
                .orderId(order.getId())
                .customerId(customer.getId())
                .paymentDate(LocalDateTime.now())
                .nominal(productPrice.getPrice())
                .status(order.getStatus())
                .build();
    }
}
