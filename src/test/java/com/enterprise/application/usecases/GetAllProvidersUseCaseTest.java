package com.enterprise.application.usecases;

import com.enterprise.application.collections.Provider;
import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.repositories.IProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class GetAllProvidersUseCaseTest {

    private GetAllProvidersUseCase getAllProvidersUseCase;

    @Autowired
    private ProviderMapper providerMapper;

    @Mock
    IProviderRepository providerRepository;

    @BeforeEach
    void setUp() {
        getAllProvidersUseCase = new GetAllProvidersUseCase(providerMapper, providerRepository);
    }

    @Test
    public void getAllProvidersTest() {

        Provider provider = new Provider("1", "andres", "8887774k", "and@hotm.com");

        ProviderDTO providerDTO = new ProviderDTO();
        providerDTO.setId(provider.getId());
        providerDTO.setName(provider.getName());
        providerDTO.setPassport(provider.getPassport());
        providerDTO.setEmail(provider.getEmail());

        Mockito.when(providerRepository.findById(provider.getId())).thenReturn(Mono.just(provider));

    }
}