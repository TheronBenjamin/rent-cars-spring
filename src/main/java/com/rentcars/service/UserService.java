package com.rentcars.service;

import com.rentcars.model.User;

import java.util.List;

public interface UserService {

    List<User> getAll();
    User getById(Integer id);
    User create(User user);
    User update(User user);
    void delete(Integer id);
    Integer getMaxId();
}
