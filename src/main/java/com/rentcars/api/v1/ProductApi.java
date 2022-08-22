package com.rentcars.api.v1;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.exception.UnknownResourceException;
import com.rentcars.mapper.ProductMapper;
import com.rentcars.service.ProductService;
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
@RequestMapping("/v1/products")
public class ProductApi {

    private final ProductService productService;
    private final ProductMapper productMapper;

    public ProductApi(ProductService productService, ProductMapper productMapper) {
        this.productService = productService;
        this.productMapper = productMapper;
    }

    @GetMapping(produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get all the products to rent")
    public ResponseEntity<List<ProductDto>> getAll(){
        return ResponseEntity.ok(
                this.productService.getAll().stream()
                        .map(this.productMapper::mapProductToDto)
                        .toList()
        );
    }


    @GetMapping(path = "/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE})
    @Operation(summary = "Get a product to rent by is ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Return the product found the given ID"),
            @ApiResponse(responseCode = "404", description = "No product found by the given ID")
    })
    public ResponseEntity<ProductDto> getById(@PathVariable Integer id){
        try {
            return ResponseEntity.ok(
                    this.productMapper
                            .mapProductToDto(this.productService.getById(id)));
        } catch (ProductNotFoundException pnte) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, pnte.getMessage());
        }
    }


    @PostMapping (
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE}
    )
    @Operation(summary = "Create a new product")
    @ApiResponse(responseCode = "201", description = "Created")
    public ResponseEntity<ProductDto> createProduct(@RequestBody final ProductDto productDto) {
        try {
            ProductDto productDtoResponse =
                    this.productMapper.mapProductToDto(
                            this.productService.create(
                                    this.productMapper.mapToProduct(productDto)
                            ));

            return ResponseEntity
                    .created(URI.create("/v1/products/" + productDtoResponse.getId()))
                    .body(productDtoResponse);
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
            productService.delete(id);
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
    public ResponseEntity<ProductDto> update(
            @RequestBody ProductDto productDto,
            @PathVariable Integer id
    ) {
        try{
            productDto.setId(id);
            return ResponseEntity.ok(productMapper.mapProductToDto(
                    productService.update(
                            productMapper.mapToProduct(productDto)))
            );
        } catch (ProductNotFoundException ex){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
