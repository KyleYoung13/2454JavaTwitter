<%-- 
    Document   : UserList
    Created on : Nov 19, 2021, 10:04:01 AM
    Author     : andre
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List Page</title>
    <link href ="resources/css.css" type ="text/css" rel ="stylesheet"/>
    </head>
    <main>
    <div class = primary_header>
        <a href = "hopperHomePage.jsp"><img src="images/beer-bubbless.jpg" alt ="hopper_header" /> </a></div>
    <nav class = secondary_header> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
        <a href="hopperServlet?action=personsHops">User Hops</a>
    </nav>
    <body>
        <div class ="secondary_header">
        <table class = "tabletwo">
            <th>
            <td>ID</td>
            <td>USERNAME</td>
            </th>
            
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.userName}" /></td> 
            </tr>
        </c:forEach>       
    </table>
        <div class ="secondary_header">
    <h2>Search User</h2></div>
    <form action="hopperServlet" method="post">
        <div class = "secondary_header">
            <label>Username</label>
            <input type="text" name="user_id"/></br> 
        </div>
        <div class = secondary_header>
            <input type='hidden' name='action' value='searchUser'/>
            <input type='submit' value='Search User'/></br>
        </div>
    </form>
    </main>
</body>
<footer class = "footer">2021 - Beer is Hydration&copy;</footer>
</html>