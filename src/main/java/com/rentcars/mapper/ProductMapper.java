package com.rentcars.mapper;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.api.dto.RentDto;
import com.rentcars.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface ProductMapper {
    @Mapping(source = "id" , target = "id")
    ProductDto mapProductToDto(Product product);
    @Mapping(source = "id" , target = "id")
    Product mapToProduct(ProductDto productDto);

    @Mapping(target = "rents", expression = "java(getRents(product))")
    default List<RentDto> getRents(Product product) {
        List<RentDto> rents = new ArrayList<>();
        if (product.getRentList() != null) {
            rents = product.getRentList().stream()
                    .map(rent -> new RentDto(
                            rent.getId(),
                            rent.getStartDateOfRent(),
                            rent.getEndDateOfRent(),
                            rent.getDescription(),
                            rent.getClient().getId(),
                            rent.getProductList().stream().map(this::mapProductToDto).toList()
                            ))
                    .toList();
        }
        return rents;
    }
}
