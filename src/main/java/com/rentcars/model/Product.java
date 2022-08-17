package com.rentcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {

    @Id
    @Column(name="id", nullable = false)
    private Integer id;

    @Column(name="name", nullable = false)
    private String name;

    @Column(name="brand", nullable = false)
    private String brand;

    @Column(name="description", nullable = false)
    private String description;

    @Column(name="price", nullable = false)
    private Float price;

    @Column(name="imgUrl", nullable = false)
    private String imgUrl;
    // think to look how to upload an img on spring

    //    private List<Rent> rentList;

}
