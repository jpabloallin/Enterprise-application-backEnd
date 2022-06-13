package com.enterprise.application.usecases;

import com.enterprise.application.model.ProviderDTO;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SaveProviderUseCaseTest {

    @MockBean
    SaveProviderUseCase saveProviderUseCase;

    @Test
    @DisplayName("saveProvider")

    void saveProvider (){
        ProviderDTO providerDTO = new ProviderDTO("1", "juan", "648A", "juan@hotmail.com");

        StepVerifier
                .create(Mono.just(Mockito.when(saveProviderUseCase.apply(providerDTO))
                        .thenReturn(Mono.just(providerDTO)))).expectComplete();

    }
}