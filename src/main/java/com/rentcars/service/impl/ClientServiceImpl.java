package com.rentcars.service.impl;

import com.rentcars.exception.ClientNotFoundException;
import com.rentcars.model.Client;
import com.rentcars.repository.ClientRepository;
import com.rentcars.service.ClientService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {


    private final ClientRepository clientRepository;

    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }


    @Override
    public List<Client> getAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client getById(Integer id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client noT found, Id is incorrect"));
    }

    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }

    @Override
    public Integer getMaxId() {
        return clientRepository.getMaxId();
    }

    @Override
    public Client create(Client client) {
        if (this.getMaxId() == null){
            client.setId(1);
        } else {
            client.setId(this.getMaxId()+1);
        }
        return clientRepository.save(client);
    }

    @Override
    public Client update(Client client) {
        Client clientToUpdate = this.getById(client.getId());
        clientToUpdate.setFirstname(client.getFirstname());
        clientToUpdate.setLastname(client.getLastname());
        clientToUpdate.setMail(client.getMail());
        clientToUpdate.setPhoneNumber(client.getPhoneNumber());
        clientToUpdate.setAddress(client.getAddress());
        clientToUpdate.setDescription(client.getDescription());
        return clientRepository.save(clientToUpdate);
    }

}
