package com.rentcars.api.dto;

import com.rentcars.model.Client;
import com.rentcars.model.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentDto {

    private Integer id;
    private LocalDate startDateOfRent;
    private LocalDate endDateOfRent;
    private String description;
    private Integer clientId;
    private List<ProductDto> products;

}
