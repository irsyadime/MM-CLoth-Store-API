package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import com.nigma.mmclothstoreapi.repository.ProductPriceRepository;
import com.nigma.mmclothstoreapi.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductPriceServiceImpl implements ProductPriceService {
    private final ProductPriceRepository priceRepository;

    @Override
    public ProductPrice create(ProductPrice productPrice) {
        return priceRepository.save(productPrice);
    }

    @Override
    public ProductPrice getById(String id) {
        return priceRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product price Not Found"));
    }

    @Override
    public ProductPrice update(ProductPrice productPrice) {
        ProductPrice existing = priceRepository.findById(productPrice.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product price Not Found"));

        existing.setId(productPrice.getId());
        return priceRepository.saveAndFlush(productPrice);
    }

    @Override
    public List<ProductPrice> getAll() {
        return priceRepository.findAll();
    }

    @Override
    public ProductPrice getByProductId(String id) {
        return priceRepository.findByProduct_Id(id);
    }
}
