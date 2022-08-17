package com.rentcars.mapper;

import com.rentcars.api.dto.ClientDto;
import com.rentcars.model.Client;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring")
public interface ClientMapper {

    Client mapDtoToClient(ClientDto clientDto);
    ClientDto mapClientToDto(Client client);

}
