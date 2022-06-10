package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.ProviderDTO;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface SaveProvider {
    Mono<ProviderDTO> apply (ProviderDTO providerDTO);
}
