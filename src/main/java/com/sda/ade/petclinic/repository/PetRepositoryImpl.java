package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.repository.base.BaseRepositoryImpl;

public class PetRepositoryImpl extends BaseRepositoryImpl<Pet,Long> implements PetRepository {
    public PetRepositoryImpl() {
        super(Pet.class);
    }

}
