package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class UserServlet extends HttpServlet {

  
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // test populate table
        ArrayList<Integer> users = new ArrayList<>();
        users.add(1);
        users.add(2);
        users.add(3);
        users.add(4);
        // test error message   
        int size = users.size();
       
        // set the attributes
        boolean editUser = false;
        request.setAttribute("users", users);
        request.setAttribute("size", size);
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
