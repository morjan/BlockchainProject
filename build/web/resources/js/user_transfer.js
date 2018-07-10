$(document).ready(function () {
    $('#transferForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            recipient: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]\d*$/i,
                        message: 'this field have to be a valid user in your contact list'
                    },
                    notEmpty: {
                        message: 'a valid recipient is require'
                    }
                }
            },
            amount: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]{1}\d*(\.\d{2})?$/i,
                        message: 'this field have to be a valid amount (12,34)'
                    },
                    notEmpty: {
                        message: 'a valid amount is require'
                    }
                }
            }
        }
    });
    
    $('#searchTransfersForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            transferStatus: {
                validators: {
                    regexp: {
                        regexp: /^[1-2]{1}$/i,
                        message: 'this field have to be a valid status "sender" or "recipient"'
                    }
                }
            },
            contact: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]\d*$/i,
                        message: 'this field have to be a valid user in your contact list'
                    }
                }
            },
            dateFrom: {
                validators: {
                    regexp: {
                        regexp: /^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$/i,
                        message: 'the date format is: yyyy-mm-dd'
                    }
                }
            },
            dateTo: {
                validators: {
                    regexp: {
                        regexp: /^([12]\d{3}-(0[1-9]|1[0-2])-(0[1-9]|[12]\d|3[01]))$/i,
                        message: 'the date format is: yyyy-mm-dd'
                    }
                }
            }
        }
    });
});