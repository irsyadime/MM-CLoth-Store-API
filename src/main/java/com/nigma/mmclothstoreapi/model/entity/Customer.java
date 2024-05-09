package com.nigma.mmclothstoreapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

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
}
