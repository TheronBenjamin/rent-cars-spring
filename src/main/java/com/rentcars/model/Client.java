package com.rentcars.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Client {

    @Id
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name="firstname", nullable = false)
    private String firstname;

    @Column(name="lastname", nullable = false)
    private String lastname;

    @Column(name="mail", nullable = false)
    private String mail;

    @Column(name="phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name="address", nullable = false)
    private String address;

    @Column(name="description", nullable = false)
    private String description;

    @OneToMany(mappedBy = "client")
    private List<Rent> rentList;

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mail='" + mail + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
