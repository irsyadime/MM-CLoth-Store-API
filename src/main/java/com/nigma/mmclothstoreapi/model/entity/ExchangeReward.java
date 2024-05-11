package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_exchange_reward")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor

public class ExchangeReward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "exchange_date")
    private LocalDateTime exchangeDate;
    @ManyToOne
    @JoinColumn(name = "customer_id")
    @JsonBackReference("customer-exchangeReward")
    private Customer customer;
    @ManyToOne
    @JoinColumn(name = "reward_id")
    @JsonBackReference("reward-exchangeReward")
    private Reward reward;

}
