package com.nigma.mmclothstoreapi.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "m_image")
@Getter
@Setter
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String name;
    private String path;
    private Long size;
    private String contentType;
}
