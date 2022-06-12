package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.ProductDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IUpdateProduct {
    Mono<ProductDTO> apply (ProductDTO productDTO);
}
