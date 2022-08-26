package com.rentcars.service;

import com.rentcars.model.Product;
import com.rentcars.model.Rent;

import java.util.List;

public interface RentService {

    List<Rent> getAll();
    Rent getById(Integer id);
    Rent create(Rent rent, Product product);
    Rent update (Rent rent);
    void delete(Integer id);
    Integer getMaxId();
    Rent enrollProductRent(Product product, Rent rent);

}
