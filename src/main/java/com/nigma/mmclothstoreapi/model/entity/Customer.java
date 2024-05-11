package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_customer")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phone;
    private Integer point;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    @JsonManagedReference("customer-orders")
    private List<Order> orders;
    @OneToOne
    @JoinColumn(name = "user_credential_id")
    private UserCredential userCredential;
    @OneToMany(mappedBy = "customer")
    @JsonManagedReference("customer-exchangeReward")
    private List<ExchangeReward> exchangeRewards;
}
