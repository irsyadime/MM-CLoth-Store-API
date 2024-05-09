package com.nigma.mmclothstoreapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Product product;
    @ManyToOne
    @JoinColumn(name = "merchant_id")
    private Merchant merchant;
    @Column(name = "is_active")
    private Boolean isActive;
}
