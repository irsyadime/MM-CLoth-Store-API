package com.nigma.mmclothstoreapi.model.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nigma.mmclothstoreapi.constant.ERole;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "m_role")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private ERole name;
    @OneToMany(mappedBy = "role")
    @JsonManagedReference("role-userCredential")
    private List<UserCredential> userCredentials;
}
