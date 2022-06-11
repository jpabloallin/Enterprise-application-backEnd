package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.ProductDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ISaveProduct {
    Mono<ProductDTO> apply(ProductDTO productDTO);
}
