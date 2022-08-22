package com.rentcars.service.impl;

import com.rentcars.exception.UserNotFoundException;

import com.rentcars.model.User;
import com.rentcars.repository.UserRepository;
import com.rentcars.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found by the given ID"));
    }

    @Override
    public Integer getMaxId() {
        return userRepository.getMaxId();
    }

    @Override
    public User create(User user) {
        User userToCreate = new User();
        if(this.getMaxId() == null){
            userToCreate.setId(1);
        } else {
            userToCreate.setId(this.getMaxId()+1);
        }
        userToCreate.setFirstname(user.getFirstname());
        userToCreate.setLastname(user.getLastname());
        userToCreate.setAdress(user.getAdress());
        userToCreate.setMail(user.getMail());
        userToCreate.setPassword(user.getPassword());
        userToCreate.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userToCreate);
    }

    @Override
    public User update(User user) {
        User userToUpdate = this.getById(user.getId());
        userToUpdate.setFirstname(user.getFirstname());
        userToUpdate.setLastname(user.getLastname());
        userToUpdate.setMail(user.getMail());
        userToUpdate.setPassword(user.getPassword());
        userToUpdate.setAdress(user.getAdress());
        userToUpdate.setPhoneNumber(user.getPhoneNumber());
        return userRepository.save(userToUpdate);
    }

    @Override
    public void delete(Integer id) {
        userRepository.deleteById(id);
    }
}
