package com.sda.ade.petclinic.repository;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.repository.base.BaseRepository;

import java.util.Optional;

public interface ClienRepository extends BaseRepository<Client,Long> {
    Optional<Client> findByFirstNameAndLastName(String firstName,String lastName);
}
