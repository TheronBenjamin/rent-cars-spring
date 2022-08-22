package com.rentcars.api.v1;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.api.dto.RentDto;
import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.exception.UnknownResourceException;
import com.rentcars.mapper.RentMapper;
import com.rentcars.service.RentService;
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
@RequestMapping("/v1/rents")
public class RentApi {

    private final RentService rentService;
    private final RentMapper rentMapper;

    public RentApi(RentService rentService, RentMapper rentMapper) {
        this.rentService = rentService;
        this.rentMapper = rentMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all the products to rent")
    public ResponseEntity<List<RentDto>> getAll(){
        return ResponseEntity.ok(
                this.rentService.getAll().stream()
                        .map(this.rentMapper::mapRentToDto)
                        .toList()
        );
    }


    @GetMapping(path = "/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get a product to rent by is ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the product found the given ID"),
            @ApiResponse(responseCode = "404", description = "No product found by the given ID")
    })
    public ResponseEntity<RentDto> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(
                    this.rentMapper
                            .mapRentToDto(this.rentService.getById(id)));
        } catch (ProductNotFoundException pnte) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, pnte.getMessage());
        }
    }


    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<RentDto> createProduct(@RequestBody final RentDto rentDto) {
        try {
            RentDto rentDtoResponse =
                    this.rentMapper.mapRentToDto(
                            this.rentService.create(
                                    this.rentMapper.mapDtoToRent(rentDto)
                            ));

            return ResponseEntity
                    .created(URI.create("/v1/products/" + rentDtoResponse.getId()))
                    .body(rentDtoResponse);
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete procuct by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Can't find the product to delete by the given ID")
    })
    public void delete(@PathVariable Integer id){
        try {
            rentService.delete(id);
            ResponseEntity.noContent().build();
        } catch (ProductNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PutMapping(path = "/{id}")
    @Operation(summary = "Update the product by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Product succesffully updated"),
            @ApiResponse(responseCode = "404", description = "Can't find the product by the given ID")

    })
    public ResponseEntity<RentDto> update(
            @RequestBody RentDto rentDto,
            @PathVariable Integer id
    ) {
        try{
            rentDto.setId(id);
            return ResponseEntity.ok(
                    rentMapper.mapRentToDto(
                        rentService.update(
                                rentMapper.mapDtoToRent(rentDto)))
            );
        } catch (ProductNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
