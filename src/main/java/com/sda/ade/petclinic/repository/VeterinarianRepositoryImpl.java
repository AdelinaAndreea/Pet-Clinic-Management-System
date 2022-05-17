package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Veterinarian;
import com.sda.ade.petclinic.repository.base.BaseRepositoryImpl;

public class VeterinarianRepositoryImpl extends BaseRepositoryImpl<Veterinarian,Long> implements VeterinarianRepository{

    public VeterinarianRepositoryImpl() {
        super(Veterinarian.class);
    }
}
