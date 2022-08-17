package com.rentcars.mapper;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel ="spring")
public interface ProductMapper {

    ProductDto mapProductToDto(Product product);
    Product mapToProduct(ProductDto productDto);

}
