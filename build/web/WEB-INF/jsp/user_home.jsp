<%-- 
    Document   : user_home
    Created on : 23-Aug-2017, 21:24:46
    Author     : morjalem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.model.pojo.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"/>">
        <script src="<c:url value="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js" />"></script>
        <script src="<c:url value="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" />"></script>
        
        <script src="<c:url value="/resources/js/user_home.js" />"></script>
        <style>
            .container {width: 1400px}
            html {font-size: 20px;}
            .panel {background: #333333;border: solid white;}
            .results {font-size: 1em;color: #FFFFFF;}
            .inline-block {display: inline-block;}
            .center {width: 90%;margin: 0 auto 30px;}
            .error {background: red;padding: 10px 15px;color: white;display: none;}
        </style>
        
        <title>International Money Transfer</title>
        
        
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li><a href="transferPage.htm"><span class="glyphicon glyphicon-transfer"></span> Transfer</a></li>
                    <li><a href="creditPage.htm"><span class="glyphicon glyphicon-credit-card"></span> Credit</a></li>
                    <li><a href="contactsPage.htm"><span class="glyphicon glyphicon-list-alt"></span> Contacts</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">                    
                    <li><a href="profilePage.htm"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                    <li><a href="logout.htm"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <div class="container well">
        <c:if test="${not empty successMessage}">
            <div class="alert alert-success text-center">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Success: </strong>${successMessage}.
            </div>
        </c:if>
        <c:if test="${not empty errorMessage}">
            <div class="alert alert-danger text-center">
                <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                <strong>Error: </strong>${errorMessage}.
            </div>
        </c:if>
            <% 
                User userConnected = (User)session.getAttribute("user");
            %>
            <div class="row">
                <div class="text-center">
                    <h1>Welcome 
                        <%out.println(userConnected.getFirstName() + " " + userConnected.getLastName());%>
                        to International Money Transfer</h1>
                </div>
                <br><br><br>
            </div>
            <div class="row">
                <div class="col col-md-6">
                    <table class="table table-striped table-hover">
                        <thead>                            
                            <tr class="header">
                                <th>CURRENCY FROM</th>
                                <th>CURRENCY TO</th>
                                <th>RATE</th>
                            </tr>
                        </thead>
                        <tbody>
                    <c:forEach var="rate" items="${ratesList}">
                            <tr>
                                <th scope="row">${rate.getCurrencyFrom().getCurrencyName()} - ${rate.getCurrencyFrom().getCurrencyCode()}</th>
                                <th>${rate.getCurrencyTo().getCurrencyName()} - ${rate.getCurrencyTo().getCurrencyCode()}</th>
                                <th>${rate.getRate()}</th>
                            </tr>
                    </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div class="col col-md-6">
                    <div class="panel panel-primary text-center">
                        <div class="panel-heading">
                            <h4 class="panel-title">Currency Converter</h4>
                        </div>
                        <div class="error">
                            Please enter numeric value
                        </div>
                        <div class="panel-body">
                            <form class="form-vertical">
                                <div class="form-group center">
                                    <input id="amount" type="number" class="amount form-control" placeholder="Enter value" min="1">
                                </div>
                                <div class="form-group inline-block">
                                    <select id="currencyFrom" class="form-control" >
                                        <c:forEach var="c" items="${currenciesList}">
                                            <option value="${c.getIdCurrency()}">${c.getCurrencyCode()} - ${c.getCurrencyName()}</option>
                                        </c:forEach>                                            
                                    </select>
                                </div>
                                <div class="form-group inline-block">
                                    <select id="currencyTo" class="form-control">
                                        <c:forEach var="c" items="${currenciesList}">
                                            <option value="${c.getIdCurrency()}">${c.getCurrencyCode()} - ${c.getCurrencyName()}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <br>
                                <div class="form-group inline-block">
                                    <button type="button" class="form-control" onclick="currencyFromFunc()">Convert</button>
                                </div>
                            </form>
                            <p id="resultConvert" class="results">0</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>