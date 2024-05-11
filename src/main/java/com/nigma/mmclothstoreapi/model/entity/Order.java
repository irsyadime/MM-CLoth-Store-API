package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.nigma.mmclothstoreapi.constant.Status;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Entity
@Table(name = "t_merchant")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "trans_date")
    private LocalDateTime transDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-orders")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "product_price_id")
    @JsonBackReference("productPrice-orders")
    private ProductPrice productPrice;
    private Integer qty;
    private Status status;

}
