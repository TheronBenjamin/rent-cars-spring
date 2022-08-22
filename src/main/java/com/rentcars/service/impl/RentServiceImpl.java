package com.rentcars.service.impl;

import com.rentcars.exception.RentNotFoundException;
import com.rentcars.model.Rent;
import com.rentcars.repository.RentRepository;
import com.rentcars.service.RentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentServiceImpl implements RentService {

    private final RentRepository rentRepository;

    public RentServiceImpl(RentRepository rentRepository) {
        this.rentRepository = rentRepository;
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
        return null;
    }

    @Override
    public Rent create(Rent rent) {
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

}
