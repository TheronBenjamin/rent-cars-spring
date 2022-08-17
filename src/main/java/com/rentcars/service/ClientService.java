package com.rentcars.service;

import com.rentcars.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClientService {

    List<Client> getAll();
    Client getById(Integer id);
    void delete (Integer id);
    Client create(Client client);
    Client update(Client client);
    Integer getMaxId();

}
