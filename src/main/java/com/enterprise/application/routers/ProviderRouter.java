package com.enterprise.application.routers;

import com.enterprise.application.collections.Provider;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.usecases.DeleteProviderUseCase;
import com.enterprise.application.usecases.GetAllProvidersUseCase;
import com.enterprise.application.usecases.SaveProviderUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springdoc.core.annotations.RouterOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.function.Function;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProviderRouter {

    //    @Bean
//    public RouterFunction<ServerResponse> saveProviderRouter(SaveProviderUseCase saveProviderUseCase){
//        //Function<Lo que recibe, lo que va a devolver>
//        Function<ProviderDTO, Mono<ServerResponse>> saveExecution = providerDTO -> saveProviderUseCase.apply(providerDTO)
//                .flatMap(result -> ServerResponse.ok().contentType(MediaType.APPLICATION_JSON).bodyValue(result));
//
//        return route(POST("/save/provider")
//                .and(accept(MediaType.APPLICATION_JSON)), request -> request.bodyToMono(ProviderDTO.class).flatMap(saveExecution));
//    }
    @Bean
    @RouterOperation(path = "/save/provider/", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = SaveProviderUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "saveProvider",
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ProviderDTO.class))),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid provider information")
                    },
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Provider.class)))
            ))

    public RouterFunction<ServerResponse> saveProviderRouter(SaveProviderUseCase saveProviderUseCase) {
        return route(
                POST("/save/provider").and(accept(MediaType.APPLICATION_JSON)),
                request -> request
                        .bodyToMono(ProviderDTO.class)
                        .flatMap(saveProviderUseCase::apply)
                        .flatMap(providerDto -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(providerDto))
        );
    }
    //GET ALL PATIENTS
//    @Bean
//    public RouterFunction<ServerResponse> getProvidersRouter(GetAllProvidersUseCase getAllProvidersUseCase){
//        return route(GET("/get/providers"), request -> ServerResponse.ok()
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromPublisher(getAllProvidersUseCase.get(),ProviderDTO.class)));
//    }

    @Bean
    @RouterOperation(path = "/get/providers", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProvidersUseCase.class,
            method = RequestMethod.GET, beanMethod = "get",
            operation = @Operation(operationId = "getProviders",
                    responses = {
                            @ApiResponse(responseCode = "200",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ProviderDTO.class)))}
            ))
    public RouterFunction<ServerResponse> getProvidersRouter(GetAllProvidersUseCase getAllProvidersUseCase){
        return route(
                GET("/get/providers").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProvidersUseCase.get(), ProviderDTO.class))
        );
    }
    //DELETE PATIENT BY ID
//    @Bean
//    RouterFunction<ServerResponse> deleteProviderRouter(DeleteProviderUseCase deleteProviderUseCase){
//        return route(DELETE("/delete/provider/{id}").and(accept(MediaType.APPLICATION_JSON)), request -> ServerResponse.status(HttpStatus.NO_CONTENT)
//                        .body(BodyInserters.fromPublisher(deleteProviderUseCase.apply(request.pathVariable("id")),Void.class)));
//
//    }
    @Bean
    @RouterOperation(path = "/delete/provider/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            beanClass = DeleteProviderUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "deleteProvider",
                    responses = {
                            @ApiResponse(
                                    responseCode = "202",
                                    description = "Successful operation"),
                            @ApiResponse(
                                    responseCode = "404",
                                    description = "Provider not found")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "id")}
            ))
    public RouterFunction<ServerResponse> deleteProviderRouter(DeleteProviderUseCase deleteProviderUseCase){
        return route(
                DELETE("/delete/provider/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProviderUseCase.apply(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
