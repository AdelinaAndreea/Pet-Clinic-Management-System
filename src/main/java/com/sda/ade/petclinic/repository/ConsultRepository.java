package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Consult;
import com.sda.ade.petclinic.repository.base.BaseRepository;

import java.util.Date;
import java.util.List;

public interface ConsultRepository extends BaseRepository<Consult, Long> {
    List<Consult> findAllWithUnvaccinatedPet();

    List<Consult> findAllByVetIdAndDateBetween(Long vetId, Date startDate, Date endDate);
}
