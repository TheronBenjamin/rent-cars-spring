package com.rentcars.service.impl;

import com.rentcars.exception.ProductNotFoundException;
import com.rentcars.exception.RentNotFoundException;
import com.rentcars.model.Product;
import com.rentcars.model.Rent;
import com.rentcars.repository.ProductRepository;
import com.rentcars.repository.RentRepository;
import com.rentcars.service.RentService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;
    private final ProductRepository productRepository;

    public RentServiceImpl(RentRepository rentRepository, ProductRepository productRepository) {
        this.rentRepository = rentRepository;
        this.productRepository = productRepository;
    }

    @Override
    public List<Rent> getAll() {
        return rentRepository.findAll();
    }

    @Override
    public Rent getById(Integer id) {
        return rentRepository.findById(id).orElseThrow(() -> new RentNotFoundException("Order not found by the given ID"));
    }

    @Override
    public Integer getMaxId() {
        return rentRepository.getMaxId();
    }

    @Override
    public Rent create(Rent rent, Product product) {
        Rent rentToCreate = new Rent();
        if(this.getMaxId() == null){
            rentToCreate.setId(1);
        } else {
            rentToCreate.setId(this.getMaxId()+1);
        }
        rentToCreate.setDescription(rent.getDescription());
        rentToCreate.setEndDateOfRent(rent.getEndDateOfRent());
        rentToCreate.setStartDateOfRent(rent.getStartDateOfRent());
        rentToCreate.setClient(rent.getClient());
        rentToCreate.setProductList(rent.getProductList());

        Product productToAdd = productRepository.findById(product.getId())
                .orElseThrow(() -> new ProductNotFoundException("No product find by the id given"));

        rentToCreate.enrolledProduct(productToAdd);

        return rentRepository.save(rentToCreate);
    }

    @Override
    public Rent update(Rent rent) {
        Rent rentToUpdate = this.getById(rent.getId());
        rentToUpdate.setDescription(rent.getDescription());
        rentToUpdate.setEndDateOfRent(rent.getEndDateOfRent());
        rentToUpdate.setStartDateOfRent(rent.getStartDateOfRent());
        rentToUpdate.setClient(rent.getClient());
        rentToUpdate.setProductList(rent.getProductList());
        return rentRepository.save(rentToUpdate);
    }

    @Override
    public void delete(Integer id) {
        rentRepository.deleteById(id);
    }

    @Override
    public Rent enrollProductRent(Product product, Rent rent) {

        return rentRepository.save(rent);
    }
}
