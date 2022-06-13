package com.enterprise.application.usecases;

import com.enterprise.application.mapper.ProviderMapper;
import com.enterprise.application.mapper.ReceiptMapper;
import com.enterprise.application.model.ReceiptDTO;
import com.enterprise.application.repositories.IProviderRepository;
import com.enterprise.application.repositories.IReceiptRepository;
import com.enterprise.application.usecases.interfaces.ISaveReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class SaveReceiptUseCase implements ISaveReceipt {
    @Autowired
    private IReceiptRepository receiptRepository;
    @Autowired
    private ReceiptMapper receiptMapper;

    @Override
    public Mono<ReceiptDTO> apply(ReceiptDTO receiptDTO) {
        return receiptRepository.save(receiptMapper.convertDTOToEntity().apply(receiptDTO)).map(receipt -> receiptMapper.convertEntityToDTO().apply(receipt));
    }
}
