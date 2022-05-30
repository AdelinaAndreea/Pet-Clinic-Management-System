package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Consult;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.model.Veterinarian;
import com.sda.ade.petclinic.repository.*;
import com.sda.ade.petclinic.service.dto.ConsultDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ConsultServiceImpl implements ConsultService {
    private final ConsultRepository consultRepository;
    private final VeterinarianRepository veterinarianRepository;
    private final PetRepository petRepository;


    public ConsultServiceImpl() {
        this.consultRepository = new ConsultRepositoryImpl();
        this.veterinarianRepository=new VeterinarianRepositoryImpl();
        this.petRepository=new PetRepositoryImpl();
    }

    @Override
    public void create(Date date, String description, Long veterinarianId, Long petId) throws InvalidParameterException {
        if (date.before(new Date())) {
            throw new InvalidParameterException("The date is in the past");
        }

        if (description == null || description.isBlank()) {
            throw new InvalidParameterException("The description is null or empty");
        }
        if (veterinarianId == null) {
            throw new InvalidParameterException("The veterinarian id is null");
        }
        if (petId == null) {
            throw new InvalidParameterException("The pet id is null");
        }

        Optional<Veterinarian> veterinarian = veterinarianRepository.findById(veterinarianId);
        if(veterinarian.isEmpty()){
            throw new InvalidParameterException("The veterinarian does not exist");
        }

        Optional<Pet> pet = petRepository.findById(petId);
        if(pet.isEmpty()){
            throw new InvalidParameterException("The pet does not exist");
        }
        Consult consult=new Consult(date,description);
        consult.setVeterinarian(veterinarian.get());
        consult.setPet(pet.get());
        consultRepository.create(consult);


    }

    @Override
    public List<ConsultDto>findAll() {
        return consultRepository.findAll().stream()
                .map(consult -> new ConsultDto(consult.getId(),
                        consult.getDate(),
                        consult.getDescription(),
                        consult.getVeterinarian().getId(),
                        consult.getPet().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Long id,String description) throws InvalidParameterException{
        if(description==null || description.isBlank()){
            throw new InvalidParameterException("The description is null or empty");
        }

        Optional<Consult> consult=consultRepository.findById(id);
        if(consult.isPresent()){
            consult.get().setDescription(description);
            consultRepository.update(consult.get());
        }
    }

    @Override
    public List<ConsultDto> findAllWithUnvaccinatedPets() {
        return consultRepository.findAllWithUnvaccinatedPet()
                .stream()
                .map(consult -> new ConsultDto(consult.getId(),
                        consult.getDate(),
                        consult.getDescription(),
                        consult.getVeterinarian().getId(),
                        consult.getPet().getId()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ConsultDto> findAllByVetIdAndDateBetween(Long vetId, Date startDate, Date endDate) throws InvalidParameterException{
        if(vetId==null){
            throw  new InvalidParameterException("Invalid parameter");
        }
        if(startDate==null){
            throw new InvalidParameterException("Invalid start date");
        }
        if(endDate==null){
            throw new InvalidParameterException("Invalid end date");
        }
        return consultRepository.findAllByVetIdAndDateBetween(vetId,startDate,endDate)
                .stream()
                .map(consult->new ConsultDto(consult.getId(),
                        consult.getDate(),
                        consult.getDescription(),
                        consult.getVeterinarian().getId(),
                        consult.getPet().getId()))
                .collect(Collectors.toList());
    }

}
