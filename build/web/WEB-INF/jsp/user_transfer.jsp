<%-- 
    Document   : user_transfer
    Created on : 23-Aug-2017, 23:20:17
    Author     : morjalem
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.model.pojo.User"%>
<%@page import="com.model.pojo.Currency"%>
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
        
        <script src="<c:url value="/resources/js/user_transfer.js" />"></script>
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
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-transfer"></span> Transfer</a></li>
                    <li><a href="creditPage.htm"><span class="glyphicon glyphicon-credit-card"></span> Credit</a></li>
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
                In this page, you can process transfer to one of your contact and view the history of your previous transfer.
            </p>
            <br>
            <div class="row">
                <div class="col col-md-6">
                    <div class="text-center"><br>
                        <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#transferModal">MAKE A TRANSFER</button>
                    </div>
                </div>
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
            </div>
            <div class="row">
                <br>
                <div class="text-center">
                    <h4><b>TRANSFER HISTORICAL</b></h4>
                </div>
                <form action="searchTransfers.htm" method="GET" id="searchTransfersForm">
                    <div class="form-group">
                        <div class="col-md-2">
                            <select name="transferStatus" class="form-control">
                                <option value="">Transfer status</option>
                                <option value="1">Sender</option>
                                <option value="2">Receiver</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <select name="contact" class="form-control">
                                <option value="">Contact</option>
                           <c:forEach var="c" items="${contactsList}">
                                <option value="${c.getIdUser()}">${c.getFirstName()} ${c.getLastName()} - ${c.getCurrency().getCurrencyCode()}</option>
                            </c:forEach>
                            </select>
                        </div>
                        <div class="col-md-2">
                            <input name="dateFrom" class="form-control"  type="text" placeholder="from (yyyy-mm-dd)">
                        </div>
                        <div class="col-md-2">
                            <input name="dateTo" class="form-control"  type="text" placeholder="to (yyyy-mm-dd)">
                        </div>
                        <div class="col-md-3">
                            <div class="text-center">
                                <button class="btn btn-success" type="submit">Search</button>
                                <button class="btn btn-primary" type="reset">Clear</button>
                            </div>
                        </div>
                    </div>
                </form>
                <table class="table table-striped table-hover">
                    <thead>
                        <tr class="header">
                            <th>TRANSFER STATUS</th>
                            <th>CONTACT</th>
                            <th>TRANSFER DATE</th>
                            <th>AMOUNT SENDER</th>
                            <th>AMOUNT RECIPIENT</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="transfer" items="${transfersList}">
                        <tr>
                            <th scope="row">
                            <c:if test = "${user.idUser == transfer.userSender.idUser}">
                                Sender
                            </c:if>
                            <c:if test = "${user.idUser == transfer.userRecipient.idUser}">
                                Recipient
                            </c:if>
                            </th>
                            <th>
                            <c:if test = "${user.idUser == transfer.userSender.idUser}">
                                ${transfer.userRecipient.firstName} ${transfer.userRecipient.lastName}
                            </c:if>
                            <c:if test = "${user.idUser == transfer.userRecipient.idUser}">
                                ${transfer.userSender.firstName} ${transfer.userSender.lastName}
                            </c:if>    
                            </th>
                            <th><fmt:formatDate type="both" value="${transfer.dateTransfer}" /></th>
                            <th>
                                ${transfer.amountSender} ${transfer.currencySender.currencyCode}
                            </th>
                            <th>
                                ${transfer.amountRecipient} ${transfer.currencyRecipient.currencyCode}
                            </th>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>                    
            </div>
            <div id="transferModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <legend><h4 class="modal-title text-center">Make a Transfer</h4></legend>
                        <p class="text-primary text-center">
                            Select a contact and the amount in your currency for the transfer.
                        </p>
                        <br>
                        <form class="form-horizontal" action="processTransfer.htm" method="POST" id="transferForm">
                            <div class="form-group">
                                <label class="col-md-5 control-label">Recipient contact:</label>
                                <div class="col-md-6 inputGroupContainer">
                                    <select name="recipient" class="form-control" >
                                        <option value="">--Select--</option>
                                    <c:forEach var="c" items="${contactsList}">
                                        <option value="${c.getIdUser()}">${c.getFirstName()} ${c.getLastName()} - ${c.getCurrency().getCurrencyCode()}</option>
                                    </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Amount of transfer:</label>
                                <div class="col-md-3 inputGroupContainer">
                                    <input name="amount" class="form-control"  type="text">
                                </div>
                            </div>
                            <div class="form-group">                             
                                <div class="text-center">
                                    <button type="submit" class="btn btn-success">CONFIRM TRANSFER</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>        
    </body>
</html>