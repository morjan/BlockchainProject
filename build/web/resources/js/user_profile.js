$(document).ready(function() {
    $('#profileForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            currency: {
                validators: {
                    notEmpty: {
                        message: 'currency is require'
                    }
                }
            },
            phoneNumber: {
                validators: {
                    regexp: {
                        regexp: /^00\d{7,25}|\d{7,25}$/i,
                        message: 'this fied has to be a valid phone number with only digits'                        
                    },
                    notEmpty: {
                        message: 'phone number is require'
                    }
                }
            }
        }
    });
    
    $('#changeEmailForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            newEmail: {
                validators: {
                    regexp: {
                        regexp: /^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/i,
                        message: 'the email format is: mail@mail.com'
                    },
                    notEmpty: {
                        message: 'email is require'
                    }
                }
            }
        }
    });
    
    $('#changePasswordForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            currentPassword: {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 25,
                        message: 'the current password must be more than 8 and less than 25 characters long'
                    },
                    notEmpty: {
                        message: 'current password is require'
                    }
                }
            },
            newPassword: {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 25,
                        message: 'the new password must be more than 8 and less than 25 characters long'
                    },
                    notEmpty: {
                        message: 'new password is require'
                    },
                    identical: {
                        field: 'newPasswordConfirm',
                        message: 'The new password and its confirm are not the same'
                    }
                }
            },
            newPasswordConfirm: {
                validators: {
                    identical: {
                        field: 'newPassword',
                        message: 'The new password and its confirm are not the same'
                    },
                    notEmpty: {
                        message: 'The new password and its confirm are not the same'
                    }
                }
            }
        }
    });
    
    $('#addDebitCardForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            nameOnCard: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\s']+$/i,
                        message: 'this field require a valid name'
                    },
                    notEmpty: {
                        message: 'a name is require'
                    }
                }
            },
            cardNumber: {
                validators: {
                    regexp: {
                        regexp: /^[\d]{16}$/i,
                        message: 'this field require a valid cb number with 16 digits'
                    },
                    notEmpty: {
                        message: 'a card number is require'
                    }
                }
            },
            dateExpire: {
                validators: {
                    regexp: {
                        regexp: /^((0[1-9])|(1[0-2]))\/(20[1-5][0-9])$/i,
                        message: 'this field require a date with the format mm/yyyy'
                    },
                    notEmpty: {
                        message: 'a date of expiration is require'
                    }
                }
            },
            cvc: {
                validators: {
                    regexp: {
                        regexp: /^[\d]{3}$/i,
                        message: 'this field require a valid cvc (123)'
                    },
                    notEmpty: {
                        message: 'a cvc number is require'
                    }
                }
            }
        }
    });
    
    $('#addBankDetailsForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            bankName: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\s']+$/i,
                        message: 'this field require a valid name of a bank'
                    },
                    notEmpty: {
                        message: 'a bank name is require'
                    }
                }
            },
            accountName: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\s']+$/i,
                        message: 'this field require a valid name og the account'
                    },
                    notEmpty: {
                        message: 'an account name is require'
                    }
                }
            },
            sortCode: {
                validators: {
                    regexp: {
                        regexp: /^[\d]{2}-[\d]{2}-[\d]{2}$/i,
                        message: 'this field require a valid sort code (12-34-56)'
                    },
                    notEmpty: {
                        message: 'a sort code is require'
                    }
                }
            },
            accountNumber: {
                validators: {
                    regexp: {
                        regexp: /^[\d]{8}$/i,
                        message: 'this field require a valid account number of 8 digits'
                    },
                    notEmpty: {
                        message: 'an account number is require'
                    }
                }
            }
        }
    });
});

function testEmailExist() {
        var email = document.getElementById("newEmail").value;
        
        $.ajax({
            type: "GET",
            url: "emailExist.htm",
            data: "email=" + email,

            success: function(response){
                if(response === "email not exist") {
                    alert("email not exist");
                } else {
                    alert("email exist");
                }
            },
            error: function(e){        }
    });
}