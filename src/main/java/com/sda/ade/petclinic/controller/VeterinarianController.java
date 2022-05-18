package com.sda.ade.petclinic.controller;

import com.sda.ade.petclinic.service.VeterinarianService;
import com.sda.ade.petclinic.service.VeterinarianServiceImpl;
import com.sda.ade.petclinic.service.dto.VeterinarianDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.lang.reflect.Array;
import java.util.List;
import java.util.Scanner;

public class VeterinarianController {
    private final VeterinarianService veterinarianService;
    private Scanner scanner;

    public VeterinarianController() {
        this.veterinarianService = new VeterinarianServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void create() {
        try {
            System.out.println("Please insert first name:");
            String firstName = scanner.nextLine();
            System.out.println("Please insert last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please insert the address:");
            String address = scanner.nextLine();
            System.out.println("Please insert the speciality:");
            String speciality = scanner.nextLine();

            veterinarianService.create(firstName, lastName, address, speciality);
            System.out.println("The veterinarian " + firstName + " was created!");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (Exception ex) {
            System.out.println("The veterinarian was not created, internal server error!");
        }

    }

    public void showAllVeterinarians() {
        List<VeterinarianDto> vets = veterinarianService.findAll();

        if (vets.isEmpty()) {
            System.out.println("No vets");
            return;
        }
        vets.stream().
                forEach(veterinarianDto ->
                        System.out.println(
                                "\nId: " + veterinarianDto.getId()
                                        + " \nFirst name: " + veterinarianDto.getFirstName()
                                        + " \nLast name: " + veterinarianDto.getLastName()
                                        + " \nAddress: " + veterinarianDto.getAddress()
                                        + " \nSpeciality: " + veterinarianDto.getSpeciality()));

    }

    public void deteleById(){
        try{
            System.out.println("Please insert the veterinarian id.");
            String idString=scanner.nextLine();
            Long id=Long.parseLong(idString);
            veterinarianService.deleteById(id);
            System.out.println("Veterinarian was successfully deleted");
        }catch (NumberFormatException e){
            System.out.println("Invalid parameter");
        }
    }

    public void  update(){
        try {
            System.out.println("Please insert the id");
            String idString=scanner.nextLine();
            Long id=Long.parseLong(idString);
            System.out.println("Please insert first name:");
            String firstName = scanner.nextLine();
            System.out.println("Please insert last name:");
            String lastName = scanner.nextLine();
            System.out.println("Please insert the address:");
            String address = scanner.nextLine();
            System.out.println("Please insert the speciality:");
            String speciality = scanner.nextLine();


            veterinarianService.update(id,firstName, lastName, address, speciality);
            System.out.println("The veterinarian " + firstName + " was updated!");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        }catch (NumberFormatException e){
            System.out.println("Invalid parameter");
        } catch (Exception ex) {
            System.out.println("The veterinarian was not updated, internal server error!");
        }
    }
}
