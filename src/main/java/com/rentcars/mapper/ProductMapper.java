package com.rentcars.mapper;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.api.dto.RentDto;
import com.rentcars.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;


@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "id" , target = "id")
    ProductDto mapProductToDto(Product product);
    @Mapping(source = "id" , target = "id")
    Product mapToProduct(ProductDto productDto);

}
