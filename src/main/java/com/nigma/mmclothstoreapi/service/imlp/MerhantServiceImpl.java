package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.entity.Merchant;
import com.nigma.mmclothstoreapi.repository.MerchantRepository;
import com.nigma.mmclothstoreapi.service.MerchantService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerhantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    @Override
    public Merchant create(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant getById(String id) {
        return merchantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant Not Found"));
    }

    @Override
    public List<Merchant> getAll() {
        return merchantRepository.findAll();
    }
}
