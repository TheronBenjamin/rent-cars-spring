package com.rentcars.service;

import com.rentcars.model.Product;
import com.rentcars.model.Rent;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Integer id);
    Product create (Product product);
    Product update (Product product);
    void delete (Integer id);
    Integer getMaxId();
}
