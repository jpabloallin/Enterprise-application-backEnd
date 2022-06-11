package com.enterprise.application.usecases;

import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.repositories.IProviderRepository;
import com.enterprise.application.usecases.interfaces.IDeleteProduct;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteProductUseCase implements IDeleteProduct {

    private final IProductRepository productRepository;

    @Override
    public Mono<Void> apply(String id) {
        return productRepository.deleteById(id);
    }
}
