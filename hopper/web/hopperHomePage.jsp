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
    <div class = primary_header>
        <a href = "hopperHomePage.jsp"><img src="images/beer-bubbless.jpg" alt ="hopper_header" /> </a></div>
    <nav class = secondary_header> 
        <a href="hopperServlet?action=userlist">List Users</a>
        <a href="hopperServlet?action=hopperHomePage">Home Page</a>
        <a href="hopperServlet?action=personsHops">User Hops</a>
    </nav>
    <body class = "container">
        <div class ="secondary_header">
            <h1>Home Page</h1></div>
        <div class ="secondary_header">
            <h2>Post Hop</h2></div>
        <form action="hopperServlet" method="post">
            <div class = secondary_header>
                <label>Type Here</label>
                <input type="text" name="content"/></br> 
            </div>
            <div class = secondary_header>
                <input type='hidden' name='action' value='addHop'/>
                <input type='submit' value='Post'/></br>
            </div>
        </form>
        <form action="/uploadImage" method="post" enctype="multipart/form-data">
            <div class = secondary_header>
                <input type="file" accept="image/*" name="file">
            </div>
            <div class = secondary_header>
                <label>&nbsp;</label>
                <input type='hidden' name='action' value='postHopImage'/>
                <input type="submit" value="Post Image"><br>
            </div>
        </form>
        <table>
            <c:forEach var="hop" items="${hopsList}">
                <tr>
                    <td>hop_id:</td>
                    <td><c:out value="${hop.id}" /></td>
                    <td>user_id:</td>
                    <td><c:out value="${hop.user_id}" /></td>
                    <td>date time:</td>
                    <td><c:out value="${hop.datetime}" /></td>
                    <td class = "tableData"><c:out value="${hop.content}" /></td>
                
                    
                    <td>likes:</td>
                    <td><c:out value="${hop.likes}" /></td>
                    <td>
                        <form action="hopperServlet" method="post">
                            <div>
                                <label>Enter Hop ID</label>
                                <input type="text" name="like_id"/></br> 
                            </div>
                            <div class>
                                <input type='hidden' name='action' value='like'/>
                                <input type='submit' value='Like Hop'/></br>
                            </div>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
