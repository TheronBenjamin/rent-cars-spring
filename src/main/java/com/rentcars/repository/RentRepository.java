package com.rentcars.repository;

import com.rentcars.model.Rent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RentRepository extends JpaRepository<Rent, Integer> {

    @Query("SELECT MAX(id) FROM Rent")
    public Integer getMaxId();

}
