package com.sda.ade.petclinic.controller;

import com.sda.ade.petclinic.service.ClientService;
import com.sda.ade.petclinic.service.ClientServiceImpl;

import java.util.Scanner;

public class ClientController {
    private final ClientService clientService;
    private final Scanner scanner;

    public ClientController() {
        this.clientService = new ClientServiceImpl();
        this.scanner = new Scanner(System.in);
    }

    public void viewAllPetsForClient() {
        try {
            System.out.println("Please insert client id");
            Long inputClientId = Long.parseLong(scanner.nextLine());
            clientService.findPetsForClientId(inputClientId)
                    .stream()
                    .forEach(pet-> System.out.println(pet));


        } catch (NumberFormatException e) {
            System.out.println("Please insert a valid client id");
        } catch (Exception e) {
            System.out.println("Internal server error");
            e.printStackTrace();
        }
    }
}