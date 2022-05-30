package com.sda.ade.petclinic;

import com.sda.ade.petclinic.controller.ClientController;
import com.sda.ade.petclinic.controller.ConsultController;
import com.sda.ade.petclinic.controller.PetController;
import com.sda.ade.petclinic.controller.VeterinarianController;
import com.sda.ade.petclinic.model.Consult;
import com.sda.ade.petclinic.option.UserOption;
import com.sda.ade.petclinic.utils.SessionManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        SessionManager.getSessionFactory();
        VeterinarianController veterinarianController=new VeterinarianController();
        PetController petController=new PetController();
        ClientController clientController=new ClientController();
        ConsultController consultController=new ConsultController();
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
                case SHOW_ALL:
                    veterinarianController.showAllVeterinarians();
                    break;
                case DELETE_BY_ID:
                    veterinarianController.deteleById();
                    break;
                case UPDATE:
                    veterinarianController.update();
                    break;
                case ADD_NEW_PET:
                    petController.addPet();
                    break;
                case VIEW_ALL_VACCINATED:
                    petController.showAllVaccinated();
                    break;
                case VIEW_ALL_PETS_FOR_CLIENT_ID:
                    clientController.viewAllPetsForClient();
                    break;
                case UNKNOWN:
                    break;
                case SHOW_ALL_PETS:
                    petController.showAllPets();
                    break;
                case DELETE_PET_BY_ID:
                    petController.deleteById();
                    break;
                case UPDATE_PET:
                    petController.update();
                    break;
                case ADD_NEW_CONSULT:
                    consultController.add();
                    break;
                case VIEW_ALL_CONSULTS:
                    consultController.showAllConsults();
                    break;
                case UPDATE_DESCRIPTION:
                    consultController.updateDescription();
                    break;
                case VIEW_ALL_CONSULTS_WITH_UNVACCINATED_PETS:
                    consultController.viewAllWithUnvaccinatedPets();
                    break;
                case VIEW_CONSULT_BY_VET_ID_AND_DATE_INTERVAL:
                    consultController.viewAllByVetIdAndDateBetween();
                    break;
                case VIEW_VET_WITH_MULTIPLE_PARAMETERS:
                    veterinarianController.viewByMultipleParameters();
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
