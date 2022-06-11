package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.repositories.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllProductsUseCase implements Supplier<Flux<ProductDTO>> {
    private ProductMapper productMapper;
    private IProductRepository productRepository;

    @Override
    public Flux<ProductDTO> get() {
        return productRepository.findAll().map(product -> productMapper.convertEntityToDTO().apply(product));
    }
}
