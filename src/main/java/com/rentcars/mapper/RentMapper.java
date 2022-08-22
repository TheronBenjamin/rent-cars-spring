package com.rentcars.mapper;

import com.rentcars.api.dto.RentDto;
import com.rentcars.model.Rent;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface RentMapper {

    Rent mapDtoToRent(RentDto rentDto);
    RentDto mapRentToDto(Rent rent);

}
