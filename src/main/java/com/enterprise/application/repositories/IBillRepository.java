package com.enterprise.application.repositories;

import com.enterprise.application.collections.Bill;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IBillRepository extends ReactiveMongoRepository<Bill, String> {
}
