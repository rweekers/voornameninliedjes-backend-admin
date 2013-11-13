var ipAddress;
var country;
var city;
var browser;
var operatingSystem;

function logVisit() {

    console.log("logging visit...");
    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
        console.log('Your IP Address is ' + data.ip);
        console.log('Your country is ' + data.country_name);
        console.log('Your city is ' + data.city);
        console.log('Your browser is ' + BrowserDetect.browser + BrowserDetect.version);
        console.log('Your operatingSystem is ' + BrowserDetect.OS);
        ipAddress = data.ip;
        browser = BrowserDetect.browser + BrowserDetect.version;
        operatingSystem = BrowserDetect.OS;
        city = data.city;
        country = data.country_name;
        storeVisit();
    }).fail(function(error) {
        console.log(error);
    });
}

function storeVisit() {
    url = "http://localhost:8080/voornaaminliedje/api/visit/add?ipAddress=" + this.ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem + "&city=" + city + "&country=" + country;

    console.log("Rest aanroep is " + url);

    /*
    $.getJSON(url, function() {
        console.log("Storing visit for ip " + ipAddress);
    });*/
}