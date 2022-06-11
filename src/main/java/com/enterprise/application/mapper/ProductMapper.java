package com.enterprise.application.mapper;

import com.enterprise.application.collections.Product;
import com.enterprise.application.collections.Provider;
import com.enterprise.application.model.ProductDTO;
import com.enterprise.application.model.ProviderDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.function.Function;
@Component
public class ProductMapper {
    private ModelMapper modelMapper;

    public ProductMapper (ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Function<Product, ProductDTO> convertEntityToDTO(){
        return product ->
                modelMapper.map(product, ProductDTO.class);
    }

    public  Function<ProductDTO, Product> convertDTOToEntity (){
        return productDTO ->
                modelMapper.map(productDTO,Product.class);
    }
}
