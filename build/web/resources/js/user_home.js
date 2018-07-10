function currencyFromFunc() {
    var amount = document.getElementById("amount").value;
    var currencyFrom = document.getElementById("currencyFrom").value;
    var currencyTo = document.getElementById("currencyTo").value;
    
    $.ajax({
        type: "GET",
        url: "convert.htm",
        data: "amount=" + amount + "&currencyFrom=" + currencyFrom + "&currencyTo=" + currencyTo,
        success: function(response){
            document.getElementById("resultConvert").innerHTML = Number(response).toFixed(2);
        },
        error: function(e){        }
    });
}