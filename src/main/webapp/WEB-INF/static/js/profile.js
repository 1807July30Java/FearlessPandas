function sendAjaxGet(url, func) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this);
        }
    };
    xhr.open("GET", url, true);
    xhr.send();
}

function populateUser(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        var div = document.getElementById("profileInfo");
        var name = document.createElement("h3");
        name.innerText = res.fName + " " + res.lName;
        var username = document.createElement("p");
        username.innerText = "Username: " + res.username;
        var email = document.createElement("p");
        email.innerText = "Email: " + res.email;
        var pemail = document.createElement("p");
        pemail.innerText = "Pay Email: " + res.payEmail;
        var address = document.createElement("p");
        var tempAd = res.addresses[0];
        address.innerText = "Address: " + tempAd.street + " " + tempAd.apartment + " " + tempAd.city + ", " + tempAd.state + " " + tempAd.zip;
        div.append(name, username, email, pemail, address);
    }
}


window.onload = function () {
    sendAjaxGet("/user/22", populateUser);
};