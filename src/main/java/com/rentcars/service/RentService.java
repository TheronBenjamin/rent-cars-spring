package com.rentcars.service;

import com.rentcars.model.Rent;

import java.util.List;

public interface RentService {

    List<Rent> getAll();
    Rent getById(Integer id);
    Rent create(Rent rent);
    Rent update (Rent rent);
    void delete(Integer id);
    Integer getMaxId();
}
