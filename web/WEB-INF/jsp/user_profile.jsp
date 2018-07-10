<%-- 
    Document   : user_profile
    Created on : 23-Aug-2017, 23:23:49
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
        
        <script src="<c:url value="/resources/js/user_profile.js" />"></script>
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
                    <li><a href="contactsPage.htm"><span class="glyphicon glyphicon-list-alt"></span> Contacts</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">                    
                    <li class="active"><a href="#"><span class="glyphicon glyphicon-user"></span> Profile</a></li>
                    <li><a href="logout.htm"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
        <c:set var="user" value="${userProfile}" />
        <c:set var="currency" value="${user.getCurrency()}" />
        <div class="container well">
            <legend><center><h2><b>PROFILE</b></h2></center></legend>
            <p class="text-primary text-center">
                In this page, you can update your personal information and manage your debit card and bank details associate to your account.
            </p>
            <br>
            <form class="form-horizontal" action="updateUser.htm" method="post" id="profileForm">
                <fieldset>
                    <input name="idUser" type="hidden" value="${user.getIdUser()}">
                    <div class="form-group">
                        <label class="col-md-4 control-label">Full Name</label>  
                        <div class="col-md-3 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="firstName" placeholder="First Name" class="form-control"  type="text" value="${user.getFirstName()}" disabled>
                            </div>
                        </div>
                        <div class="col-md-3 inputGroupContainer">
                            <div class="input-group">
                                <input  name="lastName" placeholder="Last Name" class="form-control"  type="text" value="${user.getLastName()}" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Email</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                <input  name="email" placeholder="mail@mail.com" class="form-control"  type="text" value="${userEmail}" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Birth Date</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                <input  name="birthDate" placeholder="yyyy/mm/dd" class="form-control"  type="text" value="${user.getBirthDate()}" disabled>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-md-4 control-label">Currency</label>
                        <div class="col-md-4 inputGroupContainer">
                            <div class="input-group">
                                <span class="input-group-addon"><i class="glyphicon glyphicon-gbp"></i></span>
                                <select class="form-control" name="currency">
                                    <c:forEach var="c" items="${currenciesList}">
                                        <c:if test="${c.getIdCurrency() == currency.getIdCurrency()}">
                                            <option value="${c.getIdCurrency()}" selected>${c.getCurrencyCode()} - ${c.getCurrencyName()}</option>
                                        </c:if>
                                        <c:if test="${c.getIdCurrency() != currency.getIdCurrency()}">
                                            <option value="${c.getIdCurrency()}">${c.getCurrencyCode()} - ${c.getCurrencyName()}</option>
                                        </c:if>                                        
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
                                <input  name="phoneNumber" placeholder="07904XXXXXX" class="form-control"  type="text" value="${user.getPhoneNumber()}">
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <div class="text-center"><br>
                            <button type="submit" class="btn btn-success">UPDATE PROFILE</button>
                        </div>
                    </div>
                </fieldset>
            </form>
            <br>
            <div class="form-group">
                <div class="text-center">
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#emailModal">CHANGE EMAIL</button>
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#passwordModal">CHANGE PASSWORD</button>
                <c:if test="${empty debitCard}">
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#addDebitCardModal">ADD DEBIT CARD</button>
                </c:if>
                <c:if test="${not empty debitCard}">
                    <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#deleteDebitCardModal">DELETE DEBIT CARD</button>
                </c:if>
                <c:if test="${empty bankDetails}">
                    <button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#addBankDetailsModal">ADD BANK DETAILS</button>
                </c:if>
                <c:if test="${not empty bankDetails}">
                    <button type="submit" class="btn btn-warning" data-toggle="modal" data-target="#deleteBankDetailsModal">DELETE BANK DETAILS</button>
                </c:if>
                </div>
            </div>
            <div id="emailModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body well">
                            <form class="form-horizontal" action="changeEmail.htm" method="POST" id="changeEmailForm">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <legend><h4 class="modal-title text-center">CHANGE EMAIL</h4></legend>
                                <br>
                                <div class="form-group">
                                    <label class="col-md-5 control-label">New Email</label>
                                    <div class="col-md-5 inputGroupContainer">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-envelope"></i></span>
                                            <input name="newEmail" id="newEmail" class="form-control"  type="text" onblur="testEmailExist()">
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-success">CHANGE EMAIL</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div id="passwordModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-body well">
                            <form class="form-horizontal" action="changePassword.htm" method="POST" id="changePasswordForm">
                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                <legend><h4 class="modal-title text-center">CHANGE PASSWORD</h4></legend><br>
                                <div class="form-group">
                                    <label class="col-md-5 control-label">Current Password</label>
                                    <div class="col-md-5 inputGroupContainer">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input name="currentPassword" class="form-control"  type="password">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5 control-label">New Password</label>
                                    <div class="col-md-5 inputGroupContainer">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input name="newPassword" class="form-control"  type="password">
                                        </div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label class="col-md-5 control-label">Confirm New Password</label>
                                    <div class="col-md-5 inputGroupContainer">
                                        <div class="input-group">
                                            <span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
                                            <input name="newPasswordConfirm" class="form-control"  type="password">
                                        </div>
                                    </div>
                                </div>
                                <br>
                                <div class="form-group">
                                    <div class="text-center">
                                        <button type="submit" class="btn btn-success">CHANGE PASSWORD</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            
            <div id="addDebitCardModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <legend><h4 class="modal-title text-center">Debit Card</h4></legend>
                        <br>
                        <form class="form-horizontal" action="addDebitCard.htm" method="POST" id="addDebitCardForm">
                            <div class="form-group">
                                <label class="col-md-5 control-label">Name on Card</label>
                                <div class="col-md-5 inputGroupContainer">
                                    <input name="nameOnCard" class="form-control"  type="text" required>
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Card Number:</label>
                                <div class="col-md-5 inputGroupContainer">
                                    <input name="cardNumber" class="form-control"  type="text" required>                                
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Date Expire:</label>
                                <div class="col-md-3 inputGroupContainer">
                                    <input name="dateExpire" class="form-control"  type="text" placeholder="mm/yyyy" required>                                
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">CVC:</label>
                                <div class="col-md-3 inputGroupContainer">
                                    <input name="cvc" class="form-control"  type="text" placeholder="123" required>                                
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="text-center">
                                    <button type="submit" class="btn btn-success">SAVE</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="addBankDetailsModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <legend><h3 class="modal-title text-center">Bank Details</h3></legend>
                        <br>
                        <form class="form-horizontal" action="addBankDetails.htm" method="POST" id="addBankDetailsForm">
                            <div class="form-group">
                                <label class="col-md-5 control-label">Bank Name:</label>
                                <div class="col-md-5 inputGroupContainer">
                                    <input name="bankName" class="form-control"  type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Account Name:</label>
                                <div class="col-md-5 inputGroupContainer">
                                    <input name="accountName" class="form-control"  type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Sort Code:</label>
                                <div class="col-md-3 inputGroupContainer">
                                    <input name="sortCode" class="form-control"  type="text">
                                </div>
                            </div>
                            <div class="form-group">
                                <label class="col-md-5 control-label">Account Number:</label>
                                <div class="col-md-3 inputGroupContainer">
                                    <input name="accountNumber" class="form-control"  type="text">
                                </div>
                            </div>
                            <br>
                            <div class="form-group">
                                <div class="text-center">
                                    <button type="submit" class="btn btn-success">SAVE</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="deleteDebitCardModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <form class="form-horizontal" action="deleteDebitCard.htm" method="POST">                           
                            <p class="text-primary text-center">
                                Confirm delete the debit card associate to your account.
                            </p>
                            <br>
                            <div class="form-group">
                                <div class="text-center">
                                    <button type="submit" class="btn btn-danger">DELETE</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
            <div id="deleteBankDetailsModal" class="modal fade" role="dialog">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <button type="button" class="close" data-dismiss="modal">&times;</button>
                        <form class="form-horizontal" action="deleteBankDetails.htm" method="POST">
                            <p class="text-primary text-center">
                                Confirm delete the bank details associate to your account.
                            </p>
                            <br>
                            <div class="form-group">
                                <div class="text-center">
                                    <button type="submit" class="btn btn-danger">DELETE</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>