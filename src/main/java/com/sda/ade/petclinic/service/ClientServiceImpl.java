package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.repository.ClienRepository;
import com.sda.ade.petclinic.repository.ClienRepositoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientServiceImpl implements ClientService{
    private final ClienRepository clientRepository;

    public ClientServiceImpl() {
        this.clientRepository = new ClienRepositoryImpl();
    }

    @Override
    public List<Pet> findPetsForClientId(Long clientId) {
        Optional<Client> client=clientRepository.findById(clientId);
        if(client.isPresent()){
            return client.get().getPets();
        }
        return new ArrayList<>();
    }
}
