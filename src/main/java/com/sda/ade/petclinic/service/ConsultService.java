package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.model.Consult;
import com.sda.ade.petclinic.service.dto.ConsultDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.util.Date;
import java.util.List;

public interface ConsultService {
    void create(Date date,String description,Long veterinarianId,Long petId)throws InvalidParameterException;

    List<ConsultDto> findAll();

    void update(Long id,String description)throws InvalidParameterException;

    List<ConsultDto> findAllWithUnvaccinatedPets();

    List<ConsultDto> findAllByVetIdAndDateBetween(Long vetId, Date startDate, Date endDate)throws InvalidParameterException;
}
