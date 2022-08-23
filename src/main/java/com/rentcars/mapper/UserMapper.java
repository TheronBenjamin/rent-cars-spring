package com.rentcars.mapper;

import com.rentcars.api.dto.UserDto;
import com.rentcars.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.stereotype.Component;

@Component
@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy =  NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    User mapDtoToUser(UserDto userDto);
    UserDto mapUserToDto(User user);

}
