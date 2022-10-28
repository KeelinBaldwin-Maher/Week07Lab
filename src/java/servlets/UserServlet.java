package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import models.*;
import services.*;

public class UserServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Retrieve users from database using the UserService class
        // If null is returned, there was a problem connecting to the database
        ArrayList<User> users = UserService.getAllUsers();
        
        // If the size of users is 0 there are no users in the database
        int size = users.size();
        
        // set user attributes
        request.setAttribute("users", users);
        request.setAttribute("size", size);
        
        // Retrieve the role names from the RoleService class
        ArrayList<Role> roles = RoleService.getAllRoles();
        
        // set role attribute
        request.setAttribute("roles", roles);
       
        // Determine if the user has selected edit
        boolean editUser = false;
        request.setAttribute("editUser", editUser);
        
        
        // Send to users.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
       // Send to users.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }



}
