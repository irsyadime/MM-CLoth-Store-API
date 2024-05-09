package com.nigma.mmclothstoreapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_merchant")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Merchant {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String phone;
}
