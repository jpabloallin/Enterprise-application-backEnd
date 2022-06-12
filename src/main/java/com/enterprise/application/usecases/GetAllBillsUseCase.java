package com.enterprise.application.usecases;

import com.enterprise.application.mapper.BillMapper;
import com.enterprise.application.mapper.ProductMapper;
import com.enterprise.application.model.BillDTO;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.repositories.IBillRepository;
import com.enterprise.application.repositories.IProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllBillsUseCase implements Supplier<Flux<BillDTO>> {
    private BillMapper billMapper;
    private IBillRepository billRepository;

    @Override
    public Flux<BillDTO> get() {
        return billRepository.findAll().map(bill -> billMapper.convertEntityToDTO().apply(bill));
    }
}
