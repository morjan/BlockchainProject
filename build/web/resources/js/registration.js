$(document).ready(function() {
    $('#registrationForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            firstName: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\s']+$/i,
                        message: 'The first name can consist of alphabetical characters and spaces only'
                    },
                    notEmpty: {
                        message: 'field require'
                    }
                }
            },
            lastName: {
                validators: {
                    regexp: {
                        regexp: /^[a-zA-Z\s']+$/i,
                        message: 'The last name can consist of alphabetical characters and spaces only'
                    },
                    notEmpty: {
                        message: 'field require'
                    }
                }
            },
            email: {
                validators: {
                    regexp: {
                        regexp: /^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/i,
                        message: 'the email format is: mail@mail.com'
                    },
                    notEmpty: {
                        message: 'email is require'
                    }
                }
            },
            birthDate: {
                validators: {
                    regexp: {
                        regexp: /^([12]\d{3}\/(0[1-9]|1[0-2])\/(0[1-9]|[12]\d|3[01]))$/i,                        
                        message: 'the date format is: yyyy/mm/dd'
                    },
                    notEmpty: {
                        message: 'birth date is require'
                    }
                }
            },
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
            },
            password: {
                validators: {
                    stringLength: {
                        min: 8,
                        max: 25,
                        message: 'the password must be more than 8 and less than 25 characters long'
                    },
                    notEmpty: {
                        message: 'password is require'
                    },
                    identical: {
                        field: 'confirmPassword',
                        message: 'The password and its confirm are not the same'
                    }
                }
            },
            confirmPassword: {
                validators: {
                    identical: {
                        field: 'password',
                        message: 'The password and its confirm are not the same'
                    },
                    notEmpty: {
                        message: 'The password and its confirm are not the same'
                    }
                }
            }
        }
    });   
});

function testEmailExist() {
        var email = document.getElementById("email").value;
        
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