var ipAddress;

function logSearchInstruction(argument) {

    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
        ipAddress = data.ip;
        storeSearchInstruction(argument);
    }).fail(function(error) {
        console.log(error);
    });
}

function storeSearchInstruction(argument) {
    url = "http://127.0.0.1:8080/voornaaminliedje/api/searchInstruction/add?ipAddress=" + this.ipAddress + "&argument=" + argument;

    $.getJSON(url, function() {
        console.log("Storing visit for ip " + ipAddress);
    });
}