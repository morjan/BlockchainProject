<%-- 
    Document   : registration
    Created on : 05-Aug-2017, 11:13:43
    Author     : morjan
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
        <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />"></script>
        <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" />"></script>

        <title>International Money Transfer</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="loginPage.htm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <li><a href="registrationPage.htm"><span class="glyphicon glyphicon-edit"></span> Registration</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <div class="well text-center">
                <h1>Welcome to International Money Transfer</h1>
                <p class="text-primary">After register and login, you will be able to send money across the world.</p>
                <br><br><br><br><br>
            </div>
        </div>
    </body>
</html>
