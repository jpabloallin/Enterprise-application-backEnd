package com.enterprise.application.routers;

import com.enterprise.application.collections.Product;
import com.enterprise.application.collections.Receipt;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.model.ReceiptDTO;
import com.enterprise.application.usecases.GetAllProductsUseCase;
import com.enterprise.application.usecases.GetAllReceiptsUseCase;
import com.enterprise.application.usecases.SaveProductUseCase;
import com.enterprise.application.usecases.SaveReceiptUseCase;
import io.swagger.v3.oas.annotations.Operation;
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
public class ReceiptRouter {

    //SAVE RECEIPT
    @Bean
    @RouterOperation(path = "/save/receipt/", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = SaveReceiptUseCase.class,
            beanMethod = "apply",
            operation = @Operation(
                    operationId = "saveReceipt",
                    responses = {
                            @ApiResponse(
                                    responseCode = "201",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ReceiptDTO.class))),
                            @ApiResponse(
                                    responseCode = "400",
                                    description = "Invalid receipt information")
                    },
                    requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Receipt.class)))
            ))

    public RouterFunction<ServerResponse> saveReceiptRouter(SaveReceiptUseCase saveReceiptUseCase) {
        return route(
                POST("/save/receipt").and(accept(MediaType.APPLICATION_JSON)),
                request -> request
                        .bodyToMono(ReceiptDTO.class)
                        .flatMap(saveReceiptUseCase::apply)
                        .flatMap(receiptDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(receiptDTO))
        );
    }
    //GET ALL RECEIPTS
    @Bean
    @RouterOperation(path = "/get/receipts", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllReceiptsUseCase.class,
            method = RequestMethod.GET, beanMethod = "get",
            operation = @Operation(operationId = "getReceipts",
                    responses = {
                            @ApiResponse(responseCode = "200",
                                    description = "Successful operation",
                                    content = @Content(schema = @Schema(implementation = ReceiptDTO.class)))}
            ))
    public RouterFunction<ServerResponse> getReceiptsRouter(GetAllReceiptsUseCase getAllReceiptsUseCase){
        return route(
                GET("/get/receipts").and(accept(MediaType.APPLICATION_JSON)),
                request -> ServerResponse
                        .status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllReceiptsUseCase.get(), ReceiptDTO.class))
        );
    }
}
