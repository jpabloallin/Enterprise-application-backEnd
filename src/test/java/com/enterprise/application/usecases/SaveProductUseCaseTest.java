package com.enterprise.application.usecases;

import com.enterprise.application.model.ProductDTO;
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
class SaveProductUseCaseTest {
    @MockBean
    SaveProductUseCase saveProductUseCase;

    @Test
    @DisplayName("saveProduct")

    void saveProvider (){
        ProductDTO productDTO = new ProductDTO("3", "martillo", "grande", 3, 1, 50, 5000.0,
                new ProviderDTO("32", "carlos", "6464646t", "carlos@j.com"));

        StepVerifier
                .create(Mono.just(Mockito.when(saveProductUseCase.apply(productDTO))
                        .thenReturn(Mono.just(productDTO)))).expectComplete();

    }

}