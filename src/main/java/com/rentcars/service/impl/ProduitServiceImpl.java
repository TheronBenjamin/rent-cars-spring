package com.rentcars.service.impl;

import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.model.Product;
import com.rentcars.repository.ProductRepository;
import com.rentcars.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProduitServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAll() {
       return productRepository.findAll();
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow(() ->  new ProductNotFoundException(""));
    }

    @Override
    public Product create(Product product) {
//        Product productToCreate = new Product();
        return productRepository.save(product);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = this.getById(product.getId());
        productToUpdate.setId(productToUpdate.getId());
        productToUpdate.setName(productToUpdate.getName());
        productToUpdate.setBrand(productToUpdate.getBrand());
        productToUpdate.setDescription(productToUpdate.getDescription());
        productToUpdate.setPrice(productToUpdate.getPrice());
        productToUpdate.setImgUrl(productToUpdate.getImgUrl());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }
}
