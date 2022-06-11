package com.enterprise.application.usecases.interfaces;

import reactor.core.publisher.Mono;

@FunctionalInterface
public interface IDeleteProduct {
    Mono<Void> apply(String id);
}
