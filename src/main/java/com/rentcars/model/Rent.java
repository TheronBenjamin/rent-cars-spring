package com.rentcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

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
//    private Float totalPrice;*
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(joinColumns={@JoinColumn(name="rent_id")},
            inverseJoinColumns = {@JoinColumn(name="product_id")})
    private List<Product> productList;

}
