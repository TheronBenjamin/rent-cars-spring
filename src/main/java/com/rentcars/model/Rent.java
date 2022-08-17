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
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="startDateOfRent", nullable = false)
    private LocalDate startDateOfRent;

    @Column(name="endDateOfRent", nullable = false)
    private LocalDate endDateOfRent;

//    private Integer numberOfDays;
    @Column(name="description", nullable = false)
    private String description;
//    private Float totalPrice;
//    private Client client;
//    private Product product ce sera une liste de produit pensez à la relation ManytoMany

}
