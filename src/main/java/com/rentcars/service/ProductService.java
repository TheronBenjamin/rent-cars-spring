package com.rentcars.service;

import com.rentcars.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();
    Product getById(Integer id);
    Product create (Product product);
    Product update (Product product);
    void delete (Integer id);


}
