package com.enterprise.application.repositories;

import com.enterprise.application.collections.Provider;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IProviderRepository extends ReactiveMongoRepository<Provider, String> {
}
