package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.usecases.interfaces.ISaveProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class SaveProductUseCase implements ISaveProduct {

    @Autowired
    IProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Override
    public Mono<ProductDTO> apply(ProductDTO productDTO) {
        return productRepository
                .save(productMapper
                        .convertDTOToEntity()
                        .apply(productDTO)).map(product -> productMapper.convertEntityToDTO()
                        .apply(product));
    }
}
