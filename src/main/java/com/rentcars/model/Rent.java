package com.rentcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Rent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private LocalDate startDateOfRent;
    private LocalDate endDateOfRent;
//    private Integer numberOfDays;
    private String description;
//    private Float totalPrice;
//    private Client client;
//    private Product product ce sera une liste de produit pensez à la relation ManytoMany

}
