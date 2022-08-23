package com.rentcars.api.v1;

import com.rentcars.api.dto.ClientDto;
import com.rentcars.exception.ClientNotFoundException;
import com.rentcars.mapper.ClientMapper;
import com.rentcars.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("v1/clients")
public class ClientApi {

//    test
    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientApi(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all the clients")
    public ResponseEntity<List<ClientDto>> getAll(){
        return ResponseEntity.ok(
                this.clientService.getAll().stream()
                        .map(clientMapper::mapClientToDto)
                        .toList()
        );
    }

    @GetMapping(path = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get a client by is ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the client found by is ID"),
            @ApiResponse(responseCode = "404", description = "No client founded by the given ID")
    })
    public ResponseEntity<ClientDto> getById(@PathVariable Integer id){
        try{
            return ResponseEntity.ok(
                    clientMapper.
                            mapClientToDto(clientService.getById(id)));
        } catch (ClientNotFoundException cnfe){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, cnfe.getMessage());
        }
    }

    @PostMapping(
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Create a new client")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client has been created"),
            @ApiResponse(responseCode = "404", description = "Impossible to create the client")
    })
    public ResponseEntity<ClientDto> create(@RequestBody final ClientDto clientDto){
        ClientDto clientToSave = clientMapper.mapClientToDto(clientService.create(clientMapper.mapDtoToClient(clientDto)));
                return ResponseEntity.created(URI.create("v1/clients/" + clientToSave.getId())).body(clientToSave);

    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client has been deleted"),
            @ApiResponse(responseCode = "404", description = "No client found by the given ID")
    })
    public void delete(@PathVariable Integer id){
        try {
            clientService.delete(id);
        } catch (ClientNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
    @PutMapping(path = "/{id}",
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE}
    )
    @Operation(summary = "Update the client by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Client have been updated"),
            @ApiResponse(responseCode = "404", description = "Can't fin the client to update by the given ID")
    })
    public ResponseEntity<ClientDto> update(
            @RequestBody ClientDto clientDto,
            @PathVariable Integer id
            ){
        try {
            clientDto.setId(id);
            return ResponseEntity.ok(
                    clientMapper.mapClientToDto(
                            clientService.update(
                                    clientMapper.mapDtoToClient(clientDto)))
            );
        } catch (ClientNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
