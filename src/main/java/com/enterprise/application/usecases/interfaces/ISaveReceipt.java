package com.enterprise.application.usecases.interfaces;

import com.enterprise.application.model.ReceiptDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface ISaveReceipt {
    Mono<ReceiptDTO> apply (ReceiptDTO receiptDTO);
}
