package com.nigma.mmclothstoreapi.model.entity;

import com.nigma.mmclothstoreapi.constant.ERole;
import lombok.*;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
@NoArgsConstructor
public class AppUser {
    private String id;
    private String username;
    private String password;
    private ERole role;

}
