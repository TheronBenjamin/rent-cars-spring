package com.rentcars.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentProductDto {

    private Integer id;
    private String name;
    private String brand;
    private String description;
    private Float price;
    private String imgUrl;

}
