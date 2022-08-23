package com.rentcars.api.dto;

import com.rentcars.model.Rent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {

    private Integer id;
    private String name;
    private String brand;
    private String description;
    private Float price;
    private String imgUrl;
//    private List<Rent> rents;
}
