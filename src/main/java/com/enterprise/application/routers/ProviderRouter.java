package com.enterprise.application.routers;

import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.usecases.GetAllProvidersUseCase;
import com.enterprise.application.usecases.SaveProviderUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProviderRouter {

    @Bean
    public RouterFunction<ServerResponse> saveProviderRouter(SaveProviderUseCase saveProviderUseCase){
        //Function<Lo que recibe, lo que va a devolver>
        Function<ProviderDTO, Mono<ServerResponse>> saveExecution = providerDTO -> saveProviderUseCase.apply(providerDTO)
                .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));

        return route(POST("/save/provider")
                .and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(ProviderDTO.class).flatMap(saveExecution));
    }
    //GET ALL PATIENTS
    @Bean
    public RouterFunction<ServerResponse> getProvidersRouter(GetAllProvidersUseCase getAllProvidersUseCase){
        return route(GET("/get/providers"), request -> ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromPublisher(getAllProvidersUseCase.get(),ProviderDTO.class)));
    }
}
