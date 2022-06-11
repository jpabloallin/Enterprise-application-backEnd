package com.enterprise.application.usecases;

import com.enterprise.application.repositories.IProviderRepository;
import com.enterprise.application.usecases.interfaces.IDeleteProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteProviderUseCase implements IDeleteProvider {
    private final IProviderRepository providerRepository;

    @Override
    public Mono<Void> apply(String id) {
        return providerRepository.deleteById(id);
    }
}
