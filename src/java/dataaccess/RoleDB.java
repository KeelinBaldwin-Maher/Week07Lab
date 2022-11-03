package dataaccess;

import java.util.*;
import javax.persistence.EntityManager;
import models.Role;

public class RoleDB {
    
    // Retrieve all rows from userdb role table
    public ArrayList<Role> getAllRoles() throws Exception {
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();

        try {
            // Find all of the roles in the database
            ArrayList<Role> roles = (ArrayList) em.createNamedQuery("Role.findAll", Role.class).getResultList();
            // Return list of roles
            return roles;
            
        } finally {
            em.close();
        }

    }

      
    public String getRoleName(int roleID) throws Exception{
        // Instantiate EntityManager
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try {
            // Find the role based on it's role id
            Role role = em.find(Role.class, roleID);
            
            // Return the role name
            return role.getRoleName();
            
        } finally {
           em.close();
        }

    }
    
}
