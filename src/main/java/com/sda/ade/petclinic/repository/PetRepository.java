package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.repository.base.BaseRepository;

import java.util.List;

public interface PetRepository extends BaseRepository<Pet,Long> {
    List<Pet> findAllPetsVaccinated();
}
