package com.enterprise.application.routers;

import com.enterprise.application.collections.Product;
import com.enterprise.application.collections.Provider;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.usecases.*;
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

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RequestPredicates.accept;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProductRouter {

    @Bean
    @RouterOperation(path = "/save/product/", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = SaveProductUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "saveProduct",
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class))),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid product information")
                    },
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Product.class)))
            ))

    public RouterFunction<ServerResponse> saveProductRouter(SaveProductUseCase saveProductRouter) {
        return route(
                POST("/save/product").and(accept(MediaType.APPLICATION_JSON)),
                request -> request
                        .bodyToMono(ProductDTO.class)
                        .flatMap(saveProductRouter::apply)
                        .flatMap(productDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(productDTO))
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
    @RouterOperation(path = "/get/products", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllProductsUseCase.class,
            method = RequestMethod.GET, beanMethod = "get",
            operation = @Operation(operationId = "getProducts",
                    responses = {
                            @ApiResponse(responseCode = "200",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ProductDTO.class)))}
            ))
    public RouterFunction<ServerResponse> getProductsRouter(GetAllProductsUseCase getAllProductsUseCase){
        return route(
                GET("/get/products").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProductsUseCase.get(), ProductDTO.class))
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
    @RouterOperation(path = "/delete/product/{id}", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.DELETE,
            beanClass = DeleteProductUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "deleteProduct",
                    responses = {
                            @ApiResponse(
                                    responseCode = "202",
                                    description = "Successful operation"),
                            @ApiResponse(
                                    responseCode = "404",
                                    description = "Product not found")
                    },
                    parameters = {
                            @Parameter(in = ParameterIn.PATH, name = "id")}
            ))
    public RouterFunction<ServerResponse> deleteProductRouter(DeleteProductUseCase deleteProductUseCase){
        return route(
                DELETE("/delete/product/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> deleteProductUseCase.apply(request.pathVariable("id"))
                        .flatMap((unused) -> ServerResponse.status(HttpStatus.ACCEPTED).build())
                        .onErrorResume(error -> ServerResponse.status(HttpStatus.NOT_FOUND).build())
        );
    }
}
