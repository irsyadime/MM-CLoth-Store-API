package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_reward")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Reward {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @Column(name = "reward_name")
    private String rewardName;
    @Column(name = "point_cost")
    private Integer pointCost;
    @OneToMany(mappedBy = "reward")
    @JsonManagedReference("reward-exchangeReward")
    private List<ExchangeReward> exchangeRewards;
}
