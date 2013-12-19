var ipAddress;
var country;
var city;
var browser;
var operatingSystem;

function logVisit() {

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
    url = "http://localhost:8080/voornaaminliedje/api/visit/add?ipAddress=" + this.ipAddress + "&browser=" + browser + "&operatingSystem=" + operatingSystem + "&city=" + city + "&country=" + country;

    $.getJSON(url, function() {
        console.log("Storing visit for ip " + ipAddress);
    });
}