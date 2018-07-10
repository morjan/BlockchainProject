$(document).ready(function() {
    $('#addContactForm').bootstrapValidator({
        fields: {
            emailContactUser: {
                validators: {
                    regexp: {
                        regexp: /^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/i,
                        message: 'the email format is: mail@mail.com'
                    },
                    notEmpty: {
                        message: 'an email is require'
                    }
                }
            }
        }
    });
    
    $('#deleteContactForm').bootstrapValidator({
        fields: {
            emailDeleteContact: {
                validators: {
                    regexp: {
                        regexp: /^[A-Za-z0-9](([_\.\-]?[a-zA-Z0-9]+)*)@([A-Za-z0-9]+)(([\.\-]?[a-zA-Z0-9]+)*)\.([A-Za-z]{2,})$/i,
                        message: 'the email format is: mail@mail.com'
                    },
                    notEmpty: {
                        message: 'an email is require'
                    }
                }
            }
        }
    });
});
    