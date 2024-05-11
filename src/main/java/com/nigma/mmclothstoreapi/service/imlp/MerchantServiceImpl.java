package com.nigma.mmclothstoreapi.service.imlp;

import com.nigma.mmclothstoreapi.model.dto.response.CustomerResponse;
import com.nigma.mmclothstoreapi.model.dto.response.MerchantResponse;
import com.nigma.mmclothstoreapi.model.entity.Merchant;
import com.nigma.mmclothstoreapi.model.entity.Order;
import com.nigma.mmclothstoreapi.model.entity.ProductPrice;
import com.nigma.mmclothstoreapi.repository.MerchantRepository;
import com.nigma.mmclothstoreapi.service.MerchantService;
import com.nigma.mmclothstoreapi.service.OrderService;
import com.nigma.mmclothstoreapi.service.ProductPriceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantServiceImpl implements MerchantService {
    private final MerchantRepository merchantRepository;
    private final ProductPriceService productPriceService;
    private final OrderService orderService;
    @Override
    public Merchant create(Merchant merchant) {
        return merchantRepository.save(merchant);
    }

    @Override
    public Merchant getById(String id) {
        return merchantRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Merchant Not Found"));
    }

    @Override
    public List<MerchantResponse> getAll() {
        List<Merchant> merchants =  merchantRepository.findAll();
        List<MerchantResponse> merchantResponses = new ArrayList<>();
        for(Merchant merchant : merchants){
            merchantResponses.add(
                    MerchantResponse.builder()
                            .id(merchant.getId())
                            .name(merchant.getName())
                            .phone(merchant.getPhone())
                            .build()
            );
        }
        return merchantResponses;
    }

    @Override
    public List<CustomerResponse> getAllCustomerBoughtProduct(String id) {
        List<CustomerResponse> customerResponses = new ArrayList<>();
        ProductPrice productPrice = productPriceService.getByProductId(id);
        for(Order order : productPrice.getOrders()){
            customerResponses.add(
                    CustomerResponse.builder()
                            .id(order.getCustomer().getName())
                            .name(order.getCustomer().getName())
                            .phone(order.getCustomer().getPhone())
                            .build()
            );
        }
        return customerResponses;
    }
}
