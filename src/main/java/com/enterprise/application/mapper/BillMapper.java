package com.enterprise.application.mapper;

import com.enterprise.application.collections.Bill;
import com.enterprise.application.collections.Receipt;
import com.enterprise.application.model.BillDTO;
import com.enterprise.application.model.ReceiptDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class BillMapper {
    @Autowired
    private ModelMapper modelMapper;

    public Function<Bill, BillDTO> convertEntityToDTO(){
        return bill ->
                modelMapper.map(bill, BillDTO.class);
    }

    public Function<BillDTO, Bill> convertDTOToEntity (){
        return billDTO ->
                modelMapper.map(billDTO,Bill.class);
    }
}
