package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.repositories.IProviderRepository;
import com.enterprise.application.usecases.interfaces.DeleteProvider;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class DeleteProviderUseCase implements DeleteProvider {
    private final IProviderRepository providerRepository;

    @Override
    public Mono<Void> apply(String id) {
        return providerRepository.deleteById(id);
    }
}
