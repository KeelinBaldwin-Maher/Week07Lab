package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import javax.servlet.http.HttpSession;
import models.*;
import services.*;

public class UserServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        setUserTable(session);
        
        
        // Retrieve the roles from the RoleService class
        // If null is returned, there was a problem connecting to the database
        ArrayList<Role> roles = RoleService.getAllRoles();
        
        // set roles attribute
        session.setAttribute("roles", roles);
       
        // Determine if the user has selected edit
        boolean editUser = false;
        request.setAttribute("editUser", editUser);
        
        
        // Send to users.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        
        // Retrieve the posted action
        String action = request.getParameter("action");
        
        switch (action) {
            case "Add":
                String email = request.getParameter("email");
                String firstName = request.getParameter("firstName");
                String lastName = request.getParameter("lastName");
                String password = request.getParameter("password");
                int roleID = Integer.parseInt(request.getParameter("role")) ;
                
                // validate data
                if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                    // If any inputs are empty set dataInvalid to true
                    request.setAttribute("dataInvalid", true);
                    
                } else if (UserService.inValidEmail(email)) {
                    // If the email is already in the database set emailAlreadyExists to true
                    request.setAttribute("emailAlreadyExists", true);
                } else {
                    // Add user to database
                    UserService.addUser(email, firstName, lastName, password, roleID);
                    // Reset user table
                    setUserTable(session);
                }
                break; 
        }
        
       // Send to users.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);
        
    }

    private void setUserTable(HttpSession session) {
        // Retrieve users from database using UserService 
        // If null is returned, there was a problem connecting to the database
        ArrayList<User> users = UserService.getAllUsers();
        
        // If the size of users is 0 there are no users in the database
        int size = users.size();
        
        // set user attributes
        session.setAttribute("users", users);
        session.setAttribute("size", size);
    }


}
