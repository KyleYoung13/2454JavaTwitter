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
    </head>
    <a href = "hopperHomePage.jsp"><img src ="images/beer-bubbles.jpg" alt ="hopper_header" /> </a>
    <nav> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
        <a href="hopperServlet?action=personsHops">User Hops</a>
    </nav>
    <body>
        <h1>User List</h1>
        <table>
            <th>
            <tr>
                <td>id</td>
                <td>username</td>
            </tr>
        </th>
        <c:forEach var="user" items="${users}">
            <tr>
                <td><c:out value="${user.id}" /></td>
                <td><c:out value="${user.userName}" /></td>
            </tr>
        </c:forEach>
    </table>    
    <h2>Update User</h2>
    <form action="hopperServlet" method="post">
        <div>
            <label>ID</label>
            <input type="text" name="id"/></br> 
            <label>Username</label>
            <input type="text" name="username"/></br> 
            <label>Password</label>
            <input type="password" name="password"/></br> 
        </div>
        <div>
            <input type='hidden' name='action' value='updateUser'/>
            <input type='submit' value='Update User'/></br>
        </div>
    </form>
    <h2>Search User</h2>
    <form action="hopperServlet" method="post">
        <div>
            <label>ID</label>
            <input type="text" name="user_id"/></br> 
        </div>
        <div>
            <input type='hidden' name='action' value='searchUser'/>
            <input type='submit' value='Search User'/></br>
        </div>
    </form>
</body>
</html>