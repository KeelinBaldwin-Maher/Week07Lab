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
        
        <%-- Inform the user if the database is empty--%>
        <c:if test="${DBEmpty == true}">
            <h3>No users found. Please add a user</h3>
        </c:if>

            <%-- Display all users in a table --%>    
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
                    <td><a href="user?edit=true">Edit</a></td>
                    <td><a href="user?delete=true">Delete</a></td> 
                </tr>
            </c:forEach>
            
        </table>    

        <%-- Display header depending on selected functionality 
        Also an option: ${editUser ? "Edit User" : "Add User"}
        --%>
        <c:choose>
            <%-- Add user --%>
            <c:when test="${addUser == true}">
                <h2>Add User</h2>
            </c:when>

            <%-- Edit user --%>
            <c:when test="${editUser == true}">
                <h2>Edit User</h2>
            </c:when>

            <%-- default --%>
            <c:otherwise>
                <h2>Add User</h2>
            </c:otherwise>
        </c:choose>

    </body>
</html>
