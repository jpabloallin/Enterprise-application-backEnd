package com.enterprise.application.repositories;

import com.enterprise.application.collections.Receipt;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface IReceiptRepository extends ReactiveMongoRepository<Receipt, String> {
}
