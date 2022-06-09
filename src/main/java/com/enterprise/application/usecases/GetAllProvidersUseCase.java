package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.repositories.IProviderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;
@Service
@AllArgsConstructor
public class GetAllProvidersUseCase implements Supplier<Flux<ProviderDTO>> {

    private ProviderMapper providerMapper;
    private IProviderRepository providerRepository;

    @Override
    public Flux<ProviderDTO> get() {
        return providerRepository.findAll().map(provider -> providerMapper.convertEntityToDTO().apply(provider));
    }
}
