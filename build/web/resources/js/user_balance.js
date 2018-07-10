$(document).ready(function() {
    $('#processBalanceOperationForm').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            typeOperation: {
                validators: {
                    regexp: {
                        regexp: /^[1-2]{1}$/i,
                        message: 'the value of this field can be "credit account" or "cash out"'
                    },
                    notEmpty: {
                        message: 'a valid type of operation is require'
                    }
                }                
            },
            amountOperation: {
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
    
    $('#searchHistoricalOperation').bootstrapValidator({
        framework: 'bootstrap',
        icon: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            typeOperation: {
                validators: {
                    regexp: {
                        regexp: /^[1-2]{1}$/i,
                        message: 'the value of this field can be "credit account" or "cash out"'
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
            },
            amountMin: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]{1}\d*(\.\d{2})?$/i,
                        message: 'this field have to be a valid amount (12 or 12.34)'
                    }
                }
            },
            amountMax: {
                validators: {
                    regexp: {
                        regexp: /^[1-9]{1}\d*(\.\d{2})?$/i,
                        message: 'this field have to be a valid amount (12 or 12.34)'
                    }
                }
            }
        }
    });
});