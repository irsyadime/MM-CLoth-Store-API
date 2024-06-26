package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_product")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    @Column(name = "is_active")
    private Boolean isActive;
    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    @JsonManagedReference("product-productPrices")
    private List<ProductPrice> productPrices;
}
