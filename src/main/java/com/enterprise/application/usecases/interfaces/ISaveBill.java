package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.BillDTO;
import com.enterprise.application.model.ProductDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ISaveBill {
    Mono<BillDTO> apply(BillDTO billDTO);
}
