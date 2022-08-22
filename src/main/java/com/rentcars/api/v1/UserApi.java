package com.rentcars.api.v1;

import com.rentcars.api.dto.UserDto;
import com.rentcars.exception.UserNotFoundException;
import com.rentcars.mapper.UserMapper;
import com.rentcars.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserApi {

    private final UserService userService;
    private final UserMapper userMapper;

    public UserApi(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all users")
    public ResponseEntity<List<UserDto>> getAll(){
        return ResponseEntity.ok(userService.getAll().stream()
                .map(userMapper::mapUserToDto)
                .toList());
    }

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get user by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the user founded by the given ID"),
            @ApiResponse(responseCode = "404", description = "User not found by the given ID")
    })
    public ResponseEntity<UserDto> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(
                    userMapper.mapUserToDto(userService.getById(id))
            );
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Create a new User")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User have been created succesfully"),
            @ApiResponse(responseCode = "404", description = "Impossible to create the user")
    })
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        try{
            return ResponseEntity.ok(
                    userMapper.mapUserToDto(
                            userService.create(
                                    userMapper.mapDtoToUser(userDto))
                            ));
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete a user by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User has been deleted succesfully"),
            @ApiResponse(responseCode = "404", description = "Impossible to delete the User. He was not found by his ID"),
    })
    public void delete(@PathVariable Integer id){
        try {
            userService.delete(id);
            ResponseEntity.noContent().build();
        } catch (UserNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Update the user by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User has been updated successfully"),
            @ApiResponse(responseCode = "404", description = "Can't find the user by the ID given")
    })
    public ResponseEntity<UserDto> update(
            @RequestBody UserDto userDto,
            @PathVariable Integer id
    ){
       try {
           userDto.setId(id);
           return ResponseEntity.ok(
                   userMapper.mapUserToDto(
                           userService.update(
                                   userMapper.mapDtoToUser(userDto)))
           );
       } catch (UserNotFoundException ex){
           throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
       }
    }

}
