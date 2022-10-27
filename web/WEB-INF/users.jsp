<%-- 
    Document   : users
    Created on : Oct 26, 2022, 11:01:05 AM
    Author     : ety
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Week 7/8 Lab</title>

        <style>
            body {
                font-family: Gill, Helvetica, sans-serif;
            }
            
            table {
                border-collapse: collapse;
            }

            th {
                background-color: rgba(210, 211, 211, 0.65);
            }

            th, td {
                padding: 0.25rem 1rem;
                border: 1px solid black;
            }

        </style>

    </head>
    <body>

        <h1>Manage Users</h1>

        <%-- Determine if the database is empty --%>
        <c:choose>
            <%-- Inform the user if the database is empty--%>
            <c:when test="${size == 0}">
                <h3>No users found. Please add a user</h3>
            </c:when>
                
           <%-- Display all users in a table if the database has users--%>
            <c:when test="${size >= 1}">    
                <table>
                    <tr>
                        <th>Email</th>
                        <th>First Name</th>
                        <th>Last Name</th>
                        <th>Role</th>
                        <th colspan="2"></th>
                    </tr>

                    <%-- Display a row for each user in the database 
                    TODO properly add users
                    REMEBER users.user.name <-- gets name --%>
                    <c:forEach var="items" items="${users}">
                        <tr>
                            <%-- User data --%>
                            <td>${users[0]}</td>
                            <td>${users[1]}</td>
                            <td>${users[2]}</td>
                            <td>${users[3]}</td>

                            <%-- Edit and delete links --%>
                            <td><a href="users?edit">Edit</a></td>
                            <td><a href="users?delete">Delete</a></td> 
                        </tr>
                    </c:forEach>
                </table>
            </c:when>  
                
        </c:choose>

        <%-- Display header depending on selected functionality --%>
        <h2> ${editUser ? "Edit User" : "Add User"} </h2>
  
              
                <%-- Add or edit user form --%>
                <form action="users" method="post">
                    
                    <%-- E-mail input, readonly if edit is true --%>
                    Email: <input type="text" ${editUser ? "readonly" : "" } name="email" value="${users}"><br>
                    
                    <%-- If in edit mode name inputs are filled in with the selected user--%>
                    First name: <input type="text" name="firstName" value="${users}"><br>
                    Last name: <input type="text" name="lastName" value="${users}"><br>
                    
                    Password: <input type="password" name="password"><br>
                    
                    <%-- If in edit mode the role is set to the role of the selected user --%>
                    Role: <select name="role">
                        <option value="systemAdmin" ${roleAdmin ? "selected" : ""}>system admin</option>
                        <option value="regularUser" ${roleRegular ? "selected" : "" }>regular user</option>
                    </select><br>
                    
                        <%-- If in edit mode show the buttons for update and cancel --%>
                        <c:choose>
                            <c:when test="${editUser}">
                                <input type="submit" value="Update" name="action">
                                <input type="submit" value="Cancel" name="action">
                            </c:when>
                            <c:otherwise>
                                <input type="submit" value="Add user" name="action">
                            </c:otherwise>
                        </c:choose>
                    
                </form>

    </body>
</html>
