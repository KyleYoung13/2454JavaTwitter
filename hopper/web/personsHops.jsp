<%-- 
    Document   : personsHops
    Created on : Nov 24, 2021, 5:31:31 PM
    Author     : andre
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of hops Page</title>
        <link href ="resources/css.css" type ="text/css" rel ="stylesheet"/>
    </head>
        <div class = primary_header>
            <a href = "hopperHomePage.jsp"><img src="images/beer-bubbless.jpg" alt ="hopper_header" /> </a></div>
    <nav class = secondary_header> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
        <a href="hopperServlet?action=personsHops">User Hops</a>
        
    </nav>
    <body class = container>
        <div class ="secondary_header">
            <h1>Search for Users</h1></div>
        <form action="hopperServlet" method="post">
            <div>
                <label>Username</label>
                <input type="text" name="user_id"/></br> 
            </div>
            <div class = secondary_header>
                <input type='hidden' name='action' value='searchUser'/>
                <input type='submit' value='Search User'/></br>
            </div>
        </form>
        <form action="hopperServlet" method="post">
            <div class = secondary_header>
                <label>ID to follow</label>
                <input type="text" name="follow_user_id"/></br> 
            </div>
            <div class = secondary_header>
                <input type='hidden' name='action' value='followUser'/>
                <input type='submit' value='Follow'/></br>
            </div>
        </form>
        <table class = secondary_header>
            <tr>
                <td>user_id:</td>
                <td>HOP:</td>
            </tr>
            <c:forEach var="hop" items="${hopList}">
                <tr>                   
                    <td><c:out value="${hop.user_id}" /></td>
                    <td><c:out value="${hop.content}" /></td>
                </tr>
            </c:forEach>
        </table>   
    </body>
    <footer class = "footer">2021 - Beer is Hydration&copy;</footer>
</html>
