package com.rentcars.mapper;

import com.rentcars.api.dto.ProductDto;
import com.rentcars.model.Product;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component
<<<<<<< HEAD
@Mapper(componentModel ="spring")
=======
@Mapper(componentModel = "spring")
>>>>>>> benjamin
public interface ProductMapper {

    ProductDto mapProductToDto(Product product);
    Product mapToProduct(ProductDto productDto);

}
