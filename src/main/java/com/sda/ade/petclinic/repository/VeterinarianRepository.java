package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Veterinarian;
import com.sda.ade.petclinic.repository.base.BaseRepository;

import javax.persistence.Column;
import java.util.List;

public interface VeterinarianRepository extends BaseRepository<Veterinarian,Long> {
    List<Veterinarian> findByMultipleParameters(String firstName,String lastName,String address,String speciality);

}
