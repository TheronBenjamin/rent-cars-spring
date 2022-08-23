package com.rentcars.mapper;

import com.rentcars.api.dto.ClientDto;
import com.rentcars.model.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface ClientMapper {

    @Mapping(source = "clientDto.id" , target = "id")
    Client mapDtoToClient(ClientDto clientDto);
    @Mapping(source = "id" , target = "id")
    ClientDto mapClientToDto(Client client);

}
