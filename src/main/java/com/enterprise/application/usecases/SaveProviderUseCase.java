package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.repositories.IProviderRepository;
import com.enterprise.application.usecases.interfaces.ISaveProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaveProviderUseCase implements ISaveProvider {
    @Autowired
    private IProviderRepository providerRepository;
    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public Mono<ProviderDTO> apply(ProviderDTO providerDTO) {
        return providerRepository
                .save(providerMapper
                        .convertDTOToEntity()
                        .apply(providerDTO)).map(provider -> providerMapper.convertEntityToDTO()
                        .apply(provider));
    }
}
