var ipAddress;
var country;
var browser;
var operatingSystem;

function logVisit() {

    console.log("logging visit...");
    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
        console.log('Your IP Address is ' + data.ip);
        console.log('Your country is ' + data.country_name);
        console.log('Your city is ' + data.city);
        console.log('Your browser is blabla ' + BrowserDetect.browser);
        ipAddress = data.ip;
    }).fail(function(error) {
        console.log(error);
    });
}

function storeVisit() {
    url = "http://localhost:8080/voornaaminliedje/api/visit/add?ipAddress=" + ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem;

    $.getJSON(url, function() {
        console.log("Storing visit for ip " + ipAddress);
    });
}