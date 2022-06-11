package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.ProviderDTO;
import reactor.core.publisher.Mono;
@FunctionalInterface
public interface ISaveProvider {
    Mono<ProviderDTO> apply (ProviderDTO providerDTO);
}
