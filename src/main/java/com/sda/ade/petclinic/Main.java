package com.sda.ade.petclinic;

import com.sda.ade.petclinic.controller.VeterinarianController;
import com.sda.ade.petclinic.option.UserOption;
import com.sda.ade.petclinic.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        VeterinarianController veterinarianController=new VeterinarianController();
        Scanner scanner=new Scanner(System.in);


        UserOption option=UserOption.UNKNOWN;
        do{
            UserOption.printAllOptions();
            System.out.println("Choose an option");
            String inputOption=scanner.nextLine();
            try{
                int userOption=Integer.parseInt(inputOption);
                option=UserOption.findBy(userOption).orElse(UserOption.UNKNOWN);
            }catch (NumberFormatException e){
                option=UserOption.UNKNOWN;
            }
            System.out.println(option.getPrettyName());

            switch (option){
                case ADD_NEW_VET:
                    veterinarianController.create();
                    break;
                case UNKNOWN:
                    break;
                case EXIT:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Option not implemented");
                    break;
            }
        }while (!option.equals(UserOption.EXIT));
        SessionManager.shutDown();

    }
}
