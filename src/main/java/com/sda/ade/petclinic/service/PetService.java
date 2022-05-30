package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.service.dto.PetDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.util.Date;
import java.util.List;

public interface PetService {
    void create(String race, Date dateOfBirth,boolean isVaccinated,String ownerFirstName,String ownerLastName) throws InvalidParameterException;

    List<Pet> findAllVaccinated();

    List<PetDto> findAll();

    void deleteById(Long id);
//TODO
    void update(Long id,String race, Date dateOfBirth, boolean isVaccinated, String ownerName) throws InvalidParameterException;

}
