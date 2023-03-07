package org.livecodeJPA.Config;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class Factory {

    private static  String PERSISTENCE_NAME = "bioskop";

    private static EntityManagerFactory emFactory;


    public static void getFactory(){
        emFactory = Persistence.createEntityManagerFactory(PERSISTENCE_NAME);
    }

    public static EntityManager start(){
        if(emFactory == null){
            getFactory();
        }
        return emFactory.createEntityManager();
    }
}
