package com.rentcars.mapper;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.api.dto.RentDto;
import com.rentcars.api.dto.RentProductDto;
import com.rentcars.model.Product;
import com.rentcars.model.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface RentMapper {
    @Mapping(source = "clientId", target = "client.id")
    Rent mapDtoToRent(RentDto rentDto);

    @Mapping(source = "client.id", target = "clientId")
    RentDto mapRentToDto(Rent rent);
    @Mapping(target = "products", expression = "java(getProducts(rent))")
    default List<ProductDto> getProducts(Rent rent) {
        List<ProductDto> products = new ArrayList<>();
        if (rent.getProductList() != null) {
            products = rent.getProductList().stream()
                    .map(prod -> new ProductDto(
                            prod.getId(),
                            prod.getName(),
                            prod.getBrand(),
                            prod.getDescription(),
                            prod.getPrice(),
                            prod.getImgUrl()
                    )).toList();
        }
        return products;
    }

}
