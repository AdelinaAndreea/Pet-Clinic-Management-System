package com.sda.ade.petclinic.controller;

import com.sda.ade.petclinic.service.PetService;
import com.sda.ade.petclinic.service.PetServiceImpl;
import com.sda.ade.petclinic.service.dto.PetDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PetController {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private final PetService petService;
    private final Scanner scanner;

    public PetController() {
        this.petService = new PetServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void addPet() {
        //String race, Date dateOfBirth, boolean isVaccinated, String ownerFirstName, String ownerLastName
        try {
            System.out.println("Please insert race");
            String raceInput = scanner.nextLine();

            System.out.println("Please insert date of birth");
            String dateInput = scanner.nextLine();
            Date dateOfBirth = FORMATTER.parse(dateInput);


            System.out.println("Please insert true if the pat is vaccinated or false otherwise");
            boolean isVaccinatedState = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Please insert the owner's first name");
            String firstName = scanner.nextLine();

            System.out.println("Please insert the owner's last name");
            String lastName = scanner.nextLine();
            petService.create(raceInput, dateOfBirth, isVaccinatedState, firstName, lastName);

        } catch (ParseException e) {
            System.out.println("Please insert a correct date of birth " + DATE_FORMAT + ".");
        } catch (InputMismatchException e) {
            System.out.println("Please insert true or false for the vaccinated status.");
        } catch (InvalidParameterException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Internat server error.");
        }
    }

    public void showAllVaccinated() {
        petService.findAllVaccinated()
                .stream()
                .forEach(pet -> System.out.println("Race: " + pet.getRace() + " Date of birth: " + FORMATTER.format(pet.getDateOfBirth()) + " Is vaccinated " + (pet.isVaccinated() ? "YES" : "NO")));
    }

    public void showAllPets() {
        List<PetDto> pets = petService.findAll();

        if (pets.isEmpty()) {
            System.out.println("No pets");
            return;
        }
        pets.stream()
                .forEach(petDto ->
                        System.out.println(
                                "Id:" + petDto.getId()
                                        + "\nRace: " + petDto.getRace()
                                        + "\nDate of birth: " + petDto.getDateOfBirth()
                                        + "\nIs vaccinated: " + petDto.isVaccinated()
                                        + "\nOwner name: " + petDto.getOwnerName()));
    }

    public void deleteById() {
        try {
            System.out.println("Please insert the pet id");
            String idString = scanner.nextLine();
            Long id = Long.parseLong(idString);
            petService.deleteById(id);
            System.out.println("The pet was successfully deleted");
        } catch (NumberFormatException e) {
            System.out.println("Invalid parameter.");
        }
    }
//TODO:
    public void update() {
        try {
            System.out.println("Please insert the id. ");
            String idString = scanner.nextLine();
            Long id = Long.parseLong(idString);

            System.out.println("Please insert race");
            String raceInput = scanner.nextLine();

            System.out.println("Please insert date of birth");
            String dateInput = scanner.nextLine();
            Date dateOfBirth = FORMATTER.parse(dateInput);

            System.out.println("Please insert true if the pet is vaccinated or false otherwise");
            boolean isVaccinatedState = Boolean.parseBoolean(scanner.nextLine());

            System.out.println("Please insert the owner's name");
            String ownerName = scanner.nextLine();
            petService.update(id, raceInput, dateOfBirth, isVaccinatedState, ownerName);
            System.out.println("The pet was updated!");
        } catch (NumberFormatException e) {
            System.out.println("Invalid parameter");
        } catch (Exception ex) {
            System.out.println("The veterinarian was not updated, internal server error!");
        }
    }

}
