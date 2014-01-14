var ipAddress;
var country;
var city;
var browser;
var operatingSystem;

$(document).ready(function() {
    // log();
    console.log("blabla1...");
});

function log() {

    var geoipUrl = "http://api.hostip.info/get_json.php";

    $.getJSON(geoipUrl, function(data) {
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
    // url = "http://127.0.0.1:8080/voornaaminliedje/api/visit/add?ipAddress=" + this.ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem + "&city=" + city + "&country=" + country;
    url = "http://127.0.0.1:8080/voornaaminliedje/api/visit/add?ipAddress=" + this.ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem + "&country=" + country;
    console.log("url " + url);

    /*
    $.getJSON(url, function() {
        console.log("Success");
    }).error(function(error) {
        console.log(error);
    });*/
    $.post(url);
    /*
    $.post(url, function(data) {
        // ipAddress = data.ip;
        // browser = BrowserDetect.browser + BrowserDetect.version;
        // operatingSystem = BrowserDetect.OS;
        // city = data.city;
        // country = data.country_name;
        // storeVisit();
    }).fail(function(error) {
        console.log(error);
    });*/
}
