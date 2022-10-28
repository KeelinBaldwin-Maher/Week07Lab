package services;

import dataaccess.RoleDB;
import java.util.*;
import models.Role;

public class RoleService {
    // Retrieve all data from about roles from the database
    public static ArrayList<Role> getAllRoles() {
        return null;
    }
    
    // Retrieve all role names from the database
    public static ArrayList<String> getAllRoleNames() {
        ArrayList<String> roleNames;
        
        try{
            roleNames = new RoleDB().getAllRoleNames();
            
        } catch (Exception ex) {
             // Return null if the database could not be reached
            System.out.println(ex);
            roleNames = null;
        }
        
      return roleNames;        
    }

    // Find the a role name based on its ID
    public static String findRoleName(int roleID) {
       String roleName;
       
       try {
           roleName = new RoleDB().getRoleName(roleID);
           
       } catch (Exception ex) {
           // Set the role name to null if the database could not be reached
           System.out.println(ex);
           roleName = "null";
       }
              
       return roleName;
    }

}
