package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.repository.ClientRepository;
import com.sda.ade.petclinic.repository.ClientRepositoryImpl;
import com.sda.ade.petclinic.repository.PetRepository;
import com.sda.ade.petclinic.repository.PetRepositoryImpl;
import com.sda.ade.petclinic.service.dto.PetDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final ClientRepository clienRepository;
    private Scanner scanner;

    public PetServiceImpl() {
        this.petRepository = new PetRepositoryImpl();
        this.clienRepository = new ClientRepositoryImpl();
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void create(String race, Date dateOfBirth, boolean isVaccinated, String ownerFirstName, String ownerLastName) throws InvalidParameterException {
        if (race == null || race.isBlank()) {
            throw new InvalidParameterException("The race is null or blank");
        }
        if (dateOfBirth == null) {
            throw new InvalidParameterException("The date of birth is null.");
        }
        if (dateOfBirth.after(new Date())) {
            throw new InvalidParameterException("The date of birth is in the future.");
        }
        if (ownerFirstName == null || ownerFirstName.isBlank()) {
            throw new InvalidParameterException("The owner's first name is null or blank.");
        }
        if (ownerLastName == null || ownerLastName.isBlank()) {
            throw new InvalidParameterException("The owner's last name is null or blank.");
        }
        Optional<Client> clientResult = clienRepository.findByFirstNameAndLastName(ownerFirstName, ownerLastName);
        if (clientResult.isEmpty()) {
            Client client = new Client(ownerFirstName, ownerLastName, null, null);
            clienRepository.create(client);
            clientResult = Optional.of(client);
        }
        Pet pet = new Pet(race, dateOfBirth, isVaccinated);
        pet.setOwner(clientResult.get());
        petRepository.create(pet);
    }

    @Override
    public List<Pet> findAllVaccinated() {
        return petRepository.findAllPetsVaccinated();
    }

    @Override
    public List<PetDto> findAll() {
        return petRepository.findAll().stream()
                .map(pet -> new PetDto(pet.getId(),
                        pet.getRace(),
                        pet.getDateOfBirth(),
                        pet.isVaccinated(),
                        pet.getOwner().getFirstName()))
                .collect(Collectors.toList());


    }

    @Override
    public void deleteById(Long id) {
        petRepository.deleteById(id);
    }
//TODO
    @Override
    public void update(Long id,String race, Date dateOfBirth, boolean isVaccinated, String ownerName) throws InvalidParameterException {
        if(race==null || race.isBlank()){
            throw new InvalidParameterException("The race is null or empty");
        }
        if (dateOfBirth.after(new Date())) {
            throw new InvalidParameterException("The date of birth is in the future.");
        }
        if(ownerName==null || ownerName.isBlank()){
            throw new InvalidParameterException("The owner name is null");
        }
        Optional<Pet> pet=petRepository.findById(id);
        if(pet.isPresent()){
            pet.get().setRace(race);
            pet.get().setDateOfBirth(dateOfBirth);
            pet.get().setVaccinated(isVaccinated);
            pet.get().getOwner().setFirstName(ownerName);
            petRepository.update(pet.get());
            clienRepository.update(pet.get().getOwner());
        }



    }


}
