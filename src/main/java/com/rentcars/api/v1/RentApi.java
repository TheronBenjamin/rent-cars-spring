package com.rentcars.api.v1;

import com.rentcars.api.dto.RentDto;
import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.exception.UnknownResourceException;
import com.rentcars.mapper.RentMapper;
import com.rentcars.model.Product;
import com.rentcars.service.ProductService;
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
    private final ProductService productService;
    private final RentMapper rentMapper;


    public RentApi(RentService rentService, ProductService productService, RentMapper rentMapper) {
        this.rentService = rentService;
        this.productService = productService;
        this.rentMapper = rentMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all the rents")
    public ResponseEntity<List<RentDto>> getAll(){
        return ResponseEntity.ok(
                this.rentService.getAll().stream()
                        .map(this.rentMapper::mapRentToDto)
                        .toList()
        );
    }


    @GetMapping(path = "/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get a rent by is ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the rent found the given ID"),
            @ApiResponse(responseCode = "404", description = "No rent found by the given ID")
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
            path = "/product/{id}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new rent")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<RentDto> createProduct(
            @RequestBody final RentDto rentDto,
            @PathVariable final Integer id
    ) {
        try {
            Product product = productService.getById(id);
            RentDto rentDtoResponse =
                    this.rentMapper.mapRentToDto(
                            this.rentService.create(
                                    this.rentMapper.mapDtoToRent(rentDto),product
                            ));

            return ResponseEntity
                    .created(URI.create("/v1/rents/" + rentDtoResponse.getId()))
                    .body(rentDtoResponse);
        } catch (UnknownResourceException ure) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ure.getMessage());
        }
    }

    @DeleteMapping(path = "/{id}")
    @Operation(summary = "Delete rent by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rent deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Can't find the rent to delete by the given ID")
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
    @Operation(summary = "Update the rent by the given ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Rent succesffully updated"),
            @ApiResponse(responseCode = "404", description = "Can't find the rent by the given ID")

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
