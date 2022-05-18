package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.repository.ClienRepository;
import com.sda.ade.petclinic.repository.ClienRepositoryImpl;
import com.sda.ade.petclinic.repository.PetRepository;
import com.sda.ade.petclinic.repository.PetRepositoryImpl;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.util.Date;
import java.util.Optional;

public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;
    private final ClienRepository clienRepository;

    public PetServiceImpl() {
        this.petRepository = new PetRepositoryImpl();
        this.clienRepository = new ClienRepositoryImpl();
    }

    @Override
    public void create(String race, Date dateOfBirth, boolean isVaccinated, String ownerFirstName, String ownerLastName) throws InvalidParameterException{
        if(race==null || race.isBlank()){
           throw new InvalidParameterException("The race is null or blank");
        }
        if(dateOfBirth==null){
            throw new InvalidParameterException("The date of birth is null.");
        }
        if(dateOfBirth.after(new Date())){
            throw new InvalidParameterException("The date of birth is in the future.");
        }
        if(ownerFirstName==null || ownerFirstName.isBlank()){
            throw new InvalidParameterException("The owner's first name is null or blank.");
        }
        if(ownerLastName==null || ownerLastName.isBlank()){
            throw new InvalidParameterException("The owner's last name is null or blank.");
        }
        Optional<Client> clientResult =clienRepository.findByFirstNameAndLastName(ownerFirstName,ownerLastName);
        if(clientResult.isEmpty()){
            Client client=new Client(ownerFirstName,ownerLastName,null,null);
            clienRepository.create(client);
            clientResult=Optional.of(client);
        }
        Pet pet=new Pet(race,dateOfBirth,isVaccinated);
        pet.setOwner(clientResult.get());
        petRepository.create(pet);
    }
}
