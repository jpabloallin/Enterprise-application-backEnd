package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.usecases.interfaces.IUpdateProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@AllArgsConstructor
public class UpdateProductUseCase implements IUpdateProduct {

    private final IProductRepository productRepository;

    private final ProductMapper productMapper;


    @Override
    public Mono<ProductDTO> apply(ProductDTO productDTO) {
        Objects.requireNonNull(productDTO.getId());
        return productRepository.save(productMapper.convertDTOToEntity().apply(productDTO))
                .map(product-> productMapper.convertEntityToDTO().apply(product));
    }
}
