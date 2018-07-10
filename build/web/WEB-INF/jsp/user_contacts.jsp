<%-- 
    Document   : user_contacts
    Created on : 23-Aug-2017, 23:23:09
    Author     : morjalem
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
        
        <script src="<c:url value="/resources/js/user_contacts.js" />"></script>
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
                    <li><a href="creditPage.htm"><span class="glyphicon glyphicon-credit-card"></span> Credit</a></li>
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-list-alt"></span> Contacts</a></li>
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
        <div class="container">
            <legend><center><h2><b>LIST OF CONTACTS</b></h2></center></legend>
            <p class="text-primary text-center">
                In this page, you can manage the list of your contacts, add and delete a contact user.
            </p>
            <br>
            <table class="table table-striped table-hover">
                <thead>
                    <tr>
                        <th>FIRST NAME</th>
                        <th>LAST NAME</th>
                        <th>EMAIL</th>
                        <th>PHONE NUMBER</th>
                    </tr>
                </thead>
                <tbody>
                <c:forEach var="cList" items="${contactsList}">
                    <c:set var="c" value="${cList[0]}" />
                    <c:set var="email" value="${cList[1]}" />
                    
                    <tr>
                        <th scope="row">${c.firstName}</th>
                        <th>${c.lastName}</th>
                        <th>${email}</th>
                        <th>${c.phoneNumber}</th>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
        <br>
        <div class="form-group">
            <div class="text-center">
                <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#addContactModal">NEW CONTACT</button>
                <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#deleteContactModal">DELETE CONTACT</button>
            </div>
        </div>
        <div id="addContactModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <legend><h4 class="modal-title text-center">NEW CONTACT</h4></legend>
                    <br>
                    <form class="form-horizontal" action="addContact.htm" method="POST" id="addContactForm">
                        <div class="form-group">
                            <label class="col-md-5 control-label">Contact Email:</label>
                            <div class="col-md-5 inputGroupContainer">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                    <input name="emailContactUser" class="form-control"  type="text" placeholder="mail@mail.com">
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <div class="text-center">
                                <button type="submit" class="btn btn-success">ADD CONTACT</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <div id="deleteContactModal" class="modal fade" role="dialog">
            <div class="modal-dialog">
                <div class="modal-content">
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                    <legend><h4 class="modal-title text-center">DELETE CONTACT</h4></legend>
                    <br>
                    <form class="form-horizontal" action="deleteContact.htm" method="POST" id="deleteContactForm">
                        <div class="form-group">
                            <label class="col-md-5 control-label">Contact Email:</label>
                            <div class="col-md-5 inputGroupContainer">
                                <div class="input-group">
                                    <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                    <input name="emailDeleteContact" class="form-control"  type="text" placeholder="mail@mail.com">
                                </div>
                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <div class="text-center">
                                <button type="submit" class="btn btn-danger">DELETE CONTACT</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
