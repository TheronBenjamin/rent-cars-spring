package com.rentcars.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClientDto {

    private Integer id;
    private String firstname;
    private String lastname;
    private String mail;
    private String phoneNumber;
    private String address;
    private String description;

}
