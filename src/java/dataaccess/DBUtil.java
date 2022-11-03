package dataaccess;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

// Based on Week 9 demo 
public class DBUtil {

   private static final EntityManagerFactory emf =
        Persistence.createEntityManagerFactory("UserPU");
        // The UserPU is the persistance unit name
    
    public static EntityManagerFactory getEmFactory() {
        return emf;
    }

}
