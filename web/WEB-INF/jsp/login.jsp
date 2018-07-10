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
        
        <script>
            .container {text-align: center;}
        </script>        
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a href="homePage.htm"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li class="active"><a href="loginPage.htm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <li><a href="registrationPage.htm"><span class="glyphicon glyphicon-edit"></span> Registration</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form class="well form-horizontal" action="login.htm" method="post" id="loginForm">
                <fieldset>
                    <div class="text-center">
                        <legend><h2><b>LOGIN</b></h2></legend>
                        <p class="text-primary">You can login-in the application with the username(email) and password provide for the registration.</p>
                    </div>
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger text-center">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Error: </strong>${errorMessage}.
                        </div>
                    </c:if>
                    <c:if test="${not empty successMessage}">
                        <div class="alert alert-success text-center">
                            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                            <strong>Success: </strong>${successMessage}.
                        </div>
                    </c:if>
                    <br><br>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Username</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="email" class="form-control"  type="text" required="true">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Password</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="password" class="form-control"  type="password" required="true">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="text-center">
                            <button type="submit" class="btn btn-success">LOGIN</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>