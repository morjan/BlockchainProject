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
        
        <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />"></script>
        <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" />"></script>
        <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />" rel="stylesheet" >
        <link href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" />" rel="stylesheet" >        
        <script src="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js" />"></script>
        <link href="<c:url value="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css" />" rel="stylesheet" >
        
        <script src="<c:url value="/resources/js/registration.js" />"></script>
        
        <title>International Money Transfer</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a href="homePage.htm"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="loginPage.htm"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-edit"></span> Registration</a></li>
                </ul>
            </div>
        </nav>
        <div class="container">
            <form class="well form-horizontal" action="saveUser.htm" method="POST" id="registrationForm">
                <fieldset>
                    <div class="text-center">
                        <legend><h2><b>REGISTRATION</b></h2></legend>
                        <p class="text-primary">Before login in the application, you need to register with the following information.</p>
                    </div>
                    <br>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Full Name</label>  
                        <div class="col-md-3 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="firstName" placeholder="First Name" class="form-control"  type="text">
                            </div>
                        </div>
                        <div class="col-md-3 inputGroupContainer">
                            <div class="input-group">
                                <input  name="lastName" placeholder="Last Name" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Birth Date</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="birthDate" placeholder="yyyy/mm/dd" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Currency</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-globe"></i></span>
                                <select class="form-control" name="currency">
                                    <c:forEach var="c" items="${currenciesList}">
                                        <option value="${c.getIdCurrency()}" selected>${c.getCurrencyCode()} - ${c.getCurrencyName()}</option>  
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Phone Number</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-phone"></i></span>
                                <input  name="phoneNumber" placeholder="07904XXXXXX" class="form-control"  type="text">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Email</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                <input  name="email" id="email" placeholder="mail@mail.com" class="form-control"  type="text" onblur="testEmailExist()">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Password</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="password" class="form-control"  type="password">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Confirm Password</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="confirmPassword" class="form-control"  type="password">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="text-center">
                            <button type="submit" class="btn btn-success">SUBMIT REGISTRATION</button>
                        </div>
                    </div>
                </fieldset>
            </form>
        </div>
    </body>
</html>