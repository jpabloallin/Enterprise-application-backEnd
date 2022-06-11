package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.mapper.ReceiptMapper;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.model.ProviderDTO;
import com.enterprise.application.model.ReceiptDTO;
import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.repositories.IReceiptRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllReceiptsUseCase implements Supplier<Flux<ReceiptDTO>> {
    private ReceiptMapper receiptMapper;
    private IReceiptRepository receiptRepository;

    @Override
    public Flux<ReceiptDTO> get() {
        return receiptRepository.findAll().map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
