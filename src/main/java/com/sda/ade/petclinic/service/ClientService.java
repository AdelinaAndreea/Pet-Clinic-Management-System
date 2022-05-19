package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Pet;

import java.util.List;

public interface ClientService {
    List<Pet> findPetsForClientId(Long clientId);
}
