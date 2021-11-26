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
    </head>
    <nav> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
    </nav>
    <body>
        <table>
            <c:forEach var="hop" items="${singlePersonHops}">
                <tr>
                    <td>user_id:</td>
                    <td><c:out value="${hop.user_id}" /></td>
                    <td>HOP:</td>
                    <td><c:out value="${hop.content}" /></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
