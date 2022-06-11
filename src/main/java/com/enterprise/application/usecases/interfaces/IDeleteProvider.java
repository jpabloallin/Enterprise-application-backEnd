package com.enterprise.application.usecases.interfaces;

import reactor.core.publisher.Mono;
@FunctionalInterface
public interface IDeleteProvider {

    Mono<Void> apply(String id);

}
