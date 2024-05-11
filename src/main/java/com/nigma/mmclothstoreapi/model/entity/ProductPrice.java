package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_product_price")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class ProductPrice {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "price",nullable = false,columnDefinition = "bigint check (price>0)")
    private Long price;
    @Column(name = "stock",nullable = false,columnDefinition = "int check (stock>0)")
    private Integer stock;
    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonBackReference("product-productPrices")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    @JsonBackReference("merchant-productPrices")
    private Merchant merchant;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "productPrice", cascade = CascadeType.ALL)
    @JsonManagedReference("productPrice-orders")
    private List<Order> orders;
}
