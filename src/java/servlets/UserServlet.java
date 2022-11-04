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
        String editUserEmail = request.getParameter("edit");
        boolean editUser = editUserEmail != null;
        
        // Retrieve selected users datas
        if (editUser) {
            // Populate form with selected user data
            User userToEdit = UserService.getUser(editUserEmail);
            // set edit user attributes
            request.setAttribute("userToEdit", userToEdit);
            request.setAttribute("editUser", editUser);
        }
        
        // Determine if the user has selected delete
        String deleteUserEmail = request.getParameter("delete");
        boolean deleteUser = deleteUserEmail != null;
        
        // Delete selected user
        if (deleteUser) {
            UserService.deleteUser(deleteUserEmail);
            // Reset user table
            setUserTable(session);
        }

        
        // Send to users.jsp
        getServletContext().getRequestDispatcher("/WEB-INF/users.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();

        // Retrieve form parameters
        String email = request.getParameter("email");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String password = request.getParameter("password");
        int roleID = Integer.parseInt(request.getParameter("role"));

        // Retrieve the posted action
        String action = request.getParameter("action");

        switch (action) {
            case "Add":
                // validate data
                if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                    // If any inputs are empty set dataInvalid to true
                    request.setAttribute("dataInvalid", true);
                    request.setAttribute("inputEmail", email);
                    request.setAttribute("inputFirstName", firstName);
                    request.setAttribute("inputLastName", lastName);

                } else if (UserService.inValidEmail(email)) {
                    // If the email is already in the database set emailAlreadyExists to true
                    request.setAttribute("emailAlreadyExists", true);
                    request.setAttribute("inputEmail", email);
                    request.setAttribute("inputFirstName", firstName);
                    request.setAttribute("inputLastName", lastName);

                } else {
                    // Add user to database
                    UserService.addUser(email, firstName, lastName, password, roleID);
                    // Reset user table
                    setUserTable(session);
                }
                break;

            case "Update":
                // Check if all fields are filled in
                if (email.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || password.isEmpty()) {
                    // Toggle error message
                    request.setAttribute("dataInvalid", true);
                    // Set the current user inputs
                    request.setAttribute("keepInput", true);
                    request.setAttribute("inputEmail", email);
                    request.setAttribute("inputFirstName", firstName);
                    request.setAttribute("inputLastName", lastName);
                    // Still keep page in edit mode for selected user
                    request.setAttribute("editUser", true);
                    request.setAttribute("userToEdit", UserService.getUser(email));

                } else {
                    // If all input fields are filled in then update user
                    UserService.updateUser(email, firstName, lastName, password, roleID);
                    // Reset user table
                    setUserTable(session);
                }
                break;
                
            case "Cancel":
                request.setAttribute("editUser", false);
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
