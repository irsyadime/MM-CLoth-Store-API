package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.dto.request.ProductRequest;
import com.nigma.mmclothstoreapi.model.dto.response.ProductResponse;
import com.nigma.mmclothstoreapi.model.entity.Merchant;
import com.nigma.mmclothstoreapi.model.entity.Product;
import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import com.nigma.mmclothstoreapi.model.mapper.MerchantMapper;
import com.nigma.mmclothstoreapi.repository.ProductRepository;
import com.nigma.mmclothstoreapi.service.MerchantService;
import com.nigma.mmclothstoreapi.service.ProductPriceService;
import com.nigma.mmclothstoreapi.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ProductPriceService productPriceService;
    private final MerchantService merchantService;
    @Override
    public ProductResponse createProductAndPrice(ProductRequest request) {
        Merchant merchant = merchantService.getById(request.getMerchantId());
        Product product = Product.builder()
                .name(request.getName())
                .isActive(true)
                .build();
        productRepository.saveAndFlush(product);
        ProductPrice productPrice = ProductPrice.builder()
                .product(product)
                .price(request.getPrice())
                .stock(request.getStock())
                .merchant(merchant)
                .isActive(true)
                .build();
        productPriceService.create(productPrice);
        return getProductResponse(product, productPrice, merchant);
    }

    private static ProductResponse getProductResponse(Product product, ProductPrice productPrice, Merchant merchant) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(productPrice.getPrice())
                .stock(productPrice.getStock())
                .isActive(product.getIsActive())
                .merchant(MerchantMapper.toDto(merchant))
                .build();
    }

    @Override
    public ProductResponse getById(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        ProductPrice productPrice = productPriceService.getByProductId(id);
        Merchant merchant = merchantService.getById(productPrice.getMerchant().getId());
        return getProductResponse(product,productPrice,merchant);
    }

    @Override
    public List<ProductResponse> getAll() {
        List<ProductResponse> productResponses = new ArrayList<>();
        List<Product> products = productRepository.findAll();
        for(Product product : products){
            ProductPrice productPrice = productPriceService.getByProductId(product.getId());
            Merchant merchant = merchantService.getById(productPrice.getMerchant().getId());
            ProductResponse productResponse = getProductResponse(product,productPrice,merchant);
            productResponses.add(productResponse);
        }
        return productResponses;
    }

    @Override
    public ProductResponse update(ProductRequest request) {
        Product product = productRepository.findById(request.getId()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));
        if(product != null){
            ProductPrice productPrice = productPriceService.getByProductId(product.getId());
            productPrice.setId(productPrice.getId());
            productPrice.setPrice(request.getPrice());
            productPrice.setStock(request.getStock());

            product.setName(request.getName());
            productPriceService.update(productPrice);
            productRepository.save(product);
            return getProductResponse(product,productPrice,merchantService.getById(request.getMerchantId()));
        }
        return null;
    }

    @Override
    public void delete(String id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product Not Found"));

        product.setIsActive(false);
        productRepository.save(product);
    }

    @Override
    public void deletePrice(String id) {
        ProductPrice productPrice = productPriceService.getById(id);
        if(productPrice!=null){
            productPrice.setId(productPrice.getId());
            productPrice.setIsActive(false);
            productPriceService.create(productPrice);
        }
    }
}
