package com.rentcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {


    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="adress", nullable = false)
    private String adress;

    @Column(name="mail", nullable = false)
    private String mail;

    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;

}