package com.sda.ade.petclinic.service;

import com.sda.ade.petclinic.service.exception.InvalidParameterException;

public interface VeterinarianService {

    void create(String firstName,String lastName, String address, String speciality) throws InvalidParameterException;


}
