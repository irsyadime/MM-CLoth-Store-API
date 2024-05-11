package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.constant.ERole;
import com.nigma.mmclothstoreapi.model.entity.Role;
import com.nigma.mmclothstoreapi.repository.RoleRepository;
import com.nigma.mmclothstoreapi.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Override
    public Role getOrSave(ERole role) {
        Optional<Role> optionalRole = roleRepository.findByName(role);
        if(optionalRole.isPresent()){
            return optionalRole.get();
        }
        Role currentRole = Role.builder().name(role).build();
        return roleRepository.saveAndFlush(currentRole);
    }
}
