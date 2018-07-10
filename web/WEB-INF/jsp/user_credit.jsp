<%-- 
    Document   : user_credit
    Created on : 23-Aug-2017, 23:21:33
    Author     : morjalem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.model.pojo.User"%>
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
        
        <script src="<c:url value="/resources/js/user_balance.js" />"></script>
        <style>
            .container {width: 1400px}
        </style>
        
        <title>International Money Transfer</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <ul class="nav navbar-nav">
                    <li><a href="homeUserPage.htm"><span class="glyphicon glyphicon-home"></span> Home</a></li>
                    <li><a href="transferPage.htm"><span class="glyphicon glyphicon-transfer"></span> Transfer</a></li>
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-credit-card"></span> Credit</a></li>
                    <li><a href="contactsPage.htm"><span class="glyphicon glyphicon-list-alt"></span> Contacts</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">                    
                    <li><a href="profilePage.htm"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                    <li><a href="logout.htm"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
    <c:if test="${not empty errorMessage}">
        <div class="alert alert-danger text-center">
            <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
            <strong>Error: </strong>${errorMessage}.
        </div>
    </c:if>
        <div class="container well">
            <legend>
                <center>
                    <h2>
                        <b>ACCOUNT BALANCE: 
                    <% 
                        User userConnected = (User)session.getAttribute("user");
                        out.println(userConnected.getCredit() + " " + userConnected.getCurrency().getCurrencyCode());
                    %>
                        </b>
                    </h2>
                </center>
            </legend>
            <p class="text-primary text-center">
                In this page, you can credit and cash out your account and also view the history of your previous operations.
            </p>
            <br>
            <div class="form-group">
                <div class="text-center"><br>
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#BalanceOperationModal">BALANCE OPERATION</button>
                </div>
            </div>
            <br>
            <center><h4><b>BALANCE HISTORICAL</b></h4></center>            
            <form action="searchBalanceOperations.htm" method="POST" id="searchHistoricalOperation">                
                <div class="form-group">
                    <div class="col-sm-2">
                        <select name="typeOperation" class="form-control">
                            <option value="">Type operation</option>
                        <c:forEach var="t" items="${typeOperation}">
                            <option value="${t.getIdTypeOperation()}">${t.getNameTypeOperation()}</option>
                        </c:forEach>
                        </select>
                    </div>
                    <div class="col-sm-2">
                        <input name="dateFrom" class="form-control"  type="text" placeholder="from (yyyy-mm-dd)">
                    </div>
                    <div class="col-sm-2">
                        <input name="dateTo" class="form-control"  type="text" placeholder="to (yyyy-mm-dd)">
                    </div>
                    <div class="col-sm-2">
                        <input name="amountMin" class="form-control"  type="text" placeholder="amount min">
                    </div>
                    <div class="col-sm-2">
                        <input name="amountMax" class="form-control"  type="text" placeholder="amount max">
                    </div>
                    <div class="col-sm-2">
                        <button class="btn btn-success" type="submit">Search</button>
                        <button class="btn btn-primary" type="reset">Clear</button>
                    </div>
                </div>
            </form>
            <br><br>
            <table class="table table-striped table-hover">
                <thead>
                    <tr class="header">
                        <th>DATE</th>
                        <th>TYPE OF OPERATION</th>
                        <th>AMOUNT</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="lo" items="${listOperation}">
                    <tr>
                        <th scope="row"><fmt:formatDate type="both" value="${lo.getDateOperation()}" /></th>
                        <th>${lo.getTypeOperation().getNameTypeOperation()}</th>
                        <th>${lo.getAmountOperation()} ${lo.getCurrencyOperation().getCurrencyCode()}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <div id="BalanceOperationModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <legend><h4 class="modal-title text-center">Balance Operation</h4></legend>
                    <br>
                    <form class="form-horizontal" action="processBalanceOperation.htm" method="POST" id="processBalanceOperationForm">
                        <div class="form-group">
                            <label class="col-md-5 control-label">Type of Operation:</label>
                            <div class="col-md-3 inputGroupContainer">
                                <select name="typeOperation" class="form-control" >
                                    <option value="">--Select--</option>
                                <c:forEach var="t" items="${typeOperation}">
                                    <option value="${t.getIdTypeOperation()}">${t.getNameTypeOperation()}</option>
                                </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-md-5 control-label">Amount of Operation:</label>
                            <div class="col-md-3 inputGroupContainer">
                                <input name="amountOperation" class="form-control"  type="text">
                            </div>
                        </div>
                        <div class="form-group">                             
                            <div class="text-center">
                                <button type="submit" class="btn btn-success">CONFIRM OPERATION</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>