package com.sda.ade.petclinic.option;

import javax.swing.text.html.Option;
import java.util.Arrays;
import java.util.Optional;

public enum UserOption {
    ADD_NEW_VET("Add new veterinarian ->", 1),
    SHOW_ALL("Display all veterinarians ->",2),
    DELETE_BY_ID("Delete vet by id", 3),
    UPDATE("Update a veterinarian",4),
    ADD_NEW_PET("Add a new pet",5),
    VIEW_ALL_VACCINATED("View all vaccinated pets",6),
    VIEW_ALL_PETS_FOR_CLIENT_ID("View all pets for client id",7),
    SHOW_ALL_PETS("Display all pets",8),
    DELETE_PET_BY_ID("Delete pet by id",9),
    UPDATE_PET("Update a pet",10),
    ADD_NEW_CONSULT("Add new consult",11),
    VIEW_ALL_CONSULTS("Display all consults",12),
    UPDATE_DESCRIPTION("Update a description",13),
    VIEW_ALL_CONSULTS_WITH_UNVACCINATED_PETS("View all consults with unvaccinated pets",14),
    VIEW_CONSULT_BY_VET_ID_AND_DATE_INTERVAL("Shows consults by vet id and date interval",15),
    VIEW_VET_WITH_MULTIPLE_PARAMETERS("Shows vets by multiple parameters",16),
    EXIT("Exit ->", 999),
    UNKNOWN("Unknown option,try again",1000);

    private final String prettyName;
    private final int optionNumber;


    UserOption(String prettyName, int optionNumber) {
        this.prettyName = prettyName;
        this.optionNumber = optionNumber;
    }

    public String getPrettyName() {
        return prettyName;
    }

    public int getOptionNumber() {
        return optionNumber;
    }

    public static void printAllOptions() {
        System.out.println("---------------------");
        Arrays.stream(UserOption.values())
                .filter(userOption -> !userOption.equals(UserOption.UNKNOWN))
                .forEach(option -> System.out.println(option.prettyName + " " + option.getOptionNumber()));

    }

    public static Optional<UserOption> findBy(int optionNumber){
      return Arrays.stream(UserOption.values())
                .filter(userOption -> userOption.getOptionNumber()==optionNumber)
                .findAny();

    }


}
