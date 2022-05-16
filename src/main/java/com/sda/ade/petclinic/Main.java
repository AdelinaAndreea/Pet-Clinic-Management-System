package com.sda.ade.petclinic;

import com.sda.ade.petclinic.utils.SessionManager;

public class Main {
    public static void main(String[] args) {
        //temporary change until we have the repository impl.
        SessionManager.getSessionFactory().openSession();


    }
}
