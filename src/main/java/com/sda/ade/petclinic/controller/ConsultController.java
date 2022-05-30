package com.sda.ade.petclinic.controller;

import com.sda.ade.petclinic.repository.ConsultRepository;
import com.sda.ade.petclinic.service.ConsultService;
import com.sda.ade.petclinic.service.ConsultServiceImpl;
import com.sda.ade.petclinic.service.dto.ConsultDto;
import com.sda.ade.petclinic.service.exception.InvalidParameterException;

import java.sql.SQLOutput;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class ConsultController {
    private static final String DATE_FORMAT = "dd-MM-yyyy";
    private static final SimpleDateFormat FORMATTER = new SimpleDateFormat(DATE_FORMAT);
    private final Scanner scanner;
    private final ConsultService consultService;

    public ConsultController() {
        this.scanner = new Scanner(System.in);
        this.consultService = new ConsultServiceImpl();
    }

    public void add() {
        try {
            System.out.println("Please insert a date.");
            String dateInput = scanner.nextLine();
            Date date = FORMATTER.parse(dateInput);

            System.out.println("Please insert a description ");
            String descriptionInput = scanner.nextLine();

            System.out.println("Please insert a veterinarian id");
            String idStringVet = scanner.nextLine();
            Long vetId = Long.parseLong(idStringVet);

            System.out.println("Please insert a pet id");
            String idStringPet = scanner.nextLine();
            Long petId = Long.parseLong(idStringPet);

            consultService.create(date, descriptionInput, vetId, petId);
            System.out.println("The consult was created.");
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        } catch (ParseException e) {
            System.out.println("Please insert a correct date");

        } catch (Exception e) {
            System.out.println("Internal server error");
        }
    }

    public void showAllConsults() {
        List<ConsultDto> consults = consultService.findAll();

        if (consults.isEmpty()) {
            System.out.println("No consults");
            return;
        }
        consults.stream()
                .forEach(consultDto ->
                        System.out.println("Id " + consultDto.getId()
                                + "\nDescription " + consultDto.getDescription()
                                + "\nDate " + consultDto.getDate()
                                + "\nVeterinarian Id " + consultDto.getVeterinarianId()
                                + "\nPet id " + consultDto.getPetId()));

    }

    public void updateDescription(){
        try{
            System.out.println("Please insert the id");
            String idString=scanner.nextLine();
            Long id=Long.parseLong(idString);

            System.out.println("Please insert the description");
            String description=scanner.nextLine();
            consultService.update(id,description);
            System.out.println("The description was updated");
        }catch (NumberFormatException e){
            System.out.println("invalid parameter");
        }catch (Exception e){
            System.out.println("The description not was updated");
        }
    }
    public void viewAllWithUnvaccinatedPets(){
        System.out.println("The consult that have unvaccinated pet's are");
        List<ConsultDto>consultDtos=consultService.findAllWithUnvaccinatedPets();
        consultDtos
                .stream()
                .forEach(consultDto-> System.out.println("The date is: "+FORMATTER.format(consultDto.getDate())+
                        "\nThe vet is"+consultDto.getVeterinarianId()));
    }
    public void viewAllByVetIdAndDateBetween(){
        try{
            System.out.println("Please insert vet id. ");
            String idInput=scanner.nextLine();
            Long id=Long.parseLong(idInput);

            System.out.println("Please insert the start date. ");
            String startInput=scanner.nextLine();
            Date startDate=FORMATTER.parse(startInput);

            System.out.println("Please insert the end date. ");
            String endInput=scanner.nextLine();
            Date endDate=FORMATTER.parse(endInput);

           List<ConsultDto>consultDtos= consultService.findAllByVetIdAndDateBetween(id,startDate,endDate);
           consultDtos.stream()
                   .forEach(consultDto -> System.out.println("The  date is "+FORMATTER.format(consultDto.getDate())));
        }catch (InvalidParameterException e){
            System.out.println(e.getMessage());
        }catch (ParseException e){
            System.out.println("Invalid date inserted");
        }catch (Exception e){
            System.out.println("Server error");
        }

    }
}
