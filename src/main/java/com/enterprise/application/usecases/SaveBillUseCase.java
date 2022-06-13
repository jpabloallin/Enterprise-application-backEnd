package com.enterprise.application.usecases;

import com.enterprise.application.mapper.BillMapper;
import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.model.BillDTO;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.repositories.IBillRepository;
import com.enterprise.application.repositories.IProductRepository;
import com.enterprise.application.usecases.interfaces.ISaveBill;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
public class SaveBillUseCase implements ISaveBill {
    @Autowired
    IBillRepository billRepository;

    @Autowired
    private BillMapper billMapper;

    @Override
    public Mono<BillDTO> apply(@Valid BillDTO billDTO) {
        return billRepository
                .save(billMapper
                        .convertDTOToEntity()
                        .apply(billDTO)).map(bill -> billMapper.convertEntityToDTO()
                        .apply(bill));
    }
}
