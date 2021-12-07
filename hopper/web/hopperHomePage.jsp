<%-- 
    Document   : hopperHomePage
    Created on : Nov 24, 2021, 10:04:59 AM
    Author     : andre
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page errorPage="error.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href ="resources/css.css" type ="text/css" rel ="stylesheet"/>
    </head>
    <a href = "hopperHomePage.jsp"><img src ="images/beer-bubbles.jpg" alt ="hopper_header" /> </a>
    <nav> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
        <a href="hopperServlet?action=personsHops">User Hops</a>
    </nav>
    <body>
        <div class ="h2">
            <h1>Home Page</h1></div>
        <div class ="h2">
        <h2>Post Hop</h2></div>
        <form action="hopperServlet" method="post">
            <div>
                <label>Type Here</label>
                <input type="text" name="content"/></br> 
            </div>
            <div>
                <input type='hidden' name='action' value='addHop'/>
                <input type='submit' value='Post'/></br>
            </div>
        </form>
        <table>
            <c:forEach var="hop" items="${hopsList}">
                <tr>
                    <td>user_id:</td>
                    <td><c:out value="${hop.user_id}" /></td>
                    <td>HOP:</td>
                    <td><c:out value="${hop.content}" /></td>
                </tr>
                <tr>
                    <td>date time:</td>
                    <td><c:out value="${hop.datetime}" /></td>
                    <td>likes:</td>
                    <td><c:out value="${hop.likes}" /></td>
                    <td>
                        <form action="hopperServlet" method="get">
                            <input type='hidden' name='action' value='like' id="${hop.id}"/>
                            <input type='submit' value='Like'/>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
