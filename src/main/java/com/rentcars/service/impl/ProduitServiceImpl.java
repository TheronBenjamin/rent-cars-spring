package com.rentcars.service.impl;

import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.model.Product;
import com.rentcars.model.Rent;
import com.rentcars.repository.ProductRepository;
import com.rentcars.repository.RentRepository;
import com.rentcars.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final RentRepository rentRepository;

    public ProduitServiceImpl(ProductRepository productRepository, RentRepository rentRepository) {
        this.productRepository = productRepository;
        this.rentRepository = rentRepository;
    }

    @Override
    public List<Product> getAll() {
       return productRepository.findAll();
    }

    @Override
    public Integer getMaxId(){
        return productRepository.getMaxId();
    }

    @Override
    public Product getById(Integer id) {
        return productRepository.findById(id).orElseThrow(
                () ->  new ProductNotFoundException("Product no found, Id is incorrect")
        );
    }

    @Override
    public Product create(Product product) {
        Product productToCreate = new Product();
        if(this.getMaxId() == null){
            productToCreate.setId(1);
        } else {
            productToCreate.setId(this.getMaxId()+1);
        }
        productToCreate.setName(product.getName());
        productToCreate.setBrand(product.getBrand());
        productToCreate.setDescription(product.getDescription());
        productToCreate.setPrice(product.getPrice());
        productToCreate.setImgUrl(product.getImgUrl());
        return productRepository.save(productToCreate);
    }

    @Override
    public Product update(Product product) {
        Product productToUpdate = this.getById(product.getId());
        productToUpdate.setName(product.getName());
        productToUpdate.setBrand(product.getBrand());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setPrice(product.getPrice());
        productToUpdate.setImgUrl(product.getImgUrl());
        return productRepository.save(productToUpdate);
    }

    @Override
    public void delete(Integer id) {
        productRepository.deleteById(id);
    }

}
