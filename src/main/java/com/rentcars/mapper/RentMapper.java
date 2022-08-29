package com.rentcars.mapper;

import com.rentcars.api.dto.RentDto;
import com.rentcars.model.Rent;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring" , nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface RentMapper {
    @Mapping(source = "clientId", target = "client.id")
    @Mapping(source = "products", target = "productList")
    Rent mapDtoToRent(RentDto rentDto);

    @Mapping(source = "id", target = "clientId")
    @Mapping(source = "productList", target = "products")
    RentDto mapRentToDto(Rent rent);

}
