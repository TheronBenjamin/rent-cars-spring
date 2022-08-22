package com.rentcars.repository;

import com.rentcars.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @Query("SELECT MAX(id) FROM User")
    public Integer getMaxId();

}
