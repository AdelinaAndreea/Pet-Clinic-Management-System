package com.sda.ade.petclinic.utils;

import com.sda.ade.petclinic.model.Client;
import com.sda.ade.petclinic.model.Consult;
import com.sda.ade.petclinic.model.Pet;
import com.sda.ade.petclinic.model.Veterinarian;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SessionManager extends AbstractSessionManager {

    private static final SessionManager INSTANCE = new SessionManager();

    private SessionManager() {
    }

    public static SessionFactory getSessionFactory() {
        return INSTANCE.getSessionFactory("pet_clinic?serverTimezone=UTC");  //timeZone
    }

    public static void shutDown() {
        INSTANCE.shutdownSessionManager();
    }

    @Override
    protected void setAnnotatedClasses(Configuration configuration) {
        // model class will be added here
        configuration.addAnnotatedClass(Veterinarian.class);
        configuration.addAnnotatedClass(Client.class);
        configuration.addAnnotatedClass(Pet.class);
        configuration.addAnnotatedClass(Consult.class);

    }
}
