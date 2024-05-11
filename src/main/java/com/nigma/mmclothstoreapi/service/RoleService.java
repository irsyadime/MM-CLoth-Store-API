package com.nigma.mmclothstoreapi.service;

import com.nigma.mmclothstoreapi.constant.ERole;
import com.nigma.mmclothstoreapi.model.entity.Role;

public interface RoleService {
    Role getOrSave(ERole role);
}
