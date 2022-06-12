package com.enterprise.application.routers;

import com.enterprise.application.collections.Bill;
import com.enterprise.application.model.BillDTO;
import com.enterprise.application.usecases.GetAllBillsUseCase;
import com.enterprise.application.usecases.SaveBillUseCase;
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
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class BillRouter {

    //save bill
    @Bean
    @RouterOperation(path = "/save/bill", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            method = RequestMethod.POST,
            beanClass = SaveBillUseCase.class,
            beanMethod = "apply",
            operation = @Operation(operationId = "createBill", responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "successful operation",
                            content = @Content(schema = @Schema(implementation = Bill.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid Recipe details supplied")}
                    , requestBody = @RequestBody(content = @Content(schema = @Schema(implementation = Bill.class)))
            ))
    public RouterFunction<ServerResponse> saveBillRouter(SaveBillUseCase saveBillUseCase) {
        return route(POST("/save/bill").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(BillDTO.class)
                        .flatMap(saveBillUseCase::apply)
                        .flatMap(billDTO -> ServerResponse.status(HttpStatus.CREATED)
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(billDTO)
                        )
                        .onErrorResume(e -> ServerResponse.status(HttpStatus.BAD_REQUEST).build())
        );
    }
    //Get Bills
    @Bean
    @RouterOperation(path = "/get/bills", produces = {
            MediaType.APPLICATION_JSON_VALUE},
            beanClass = GetAllBillsUseCase.class,
            method = RequestMethod.GET,
            beanMethod = "get",
            operation = @Operation(operationId = "getBills", responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "successful operation",
                            content = @Content(schema = @Schema(implementation = BillDTO.class)))}
            ))
    public RouterFunction<ServerResponse> getBillsRouter(GetAllBillsUseCase getAllBillsUseCase) {
        return route(GET("/get/bills"),
                request -> ServerResponse.status(HttpStatus.OK)
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromProducer(getAllBillsUseCase.get(), BillDTO.class))
        );
    }
}
