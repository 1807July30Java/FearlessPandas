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

function setDefaults(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);
        document.getElementById("username").defaultValue = res.username;
        document.getElementById("firstName").defaultValue = res.fName;
        document.getElementById("lastName").defaultValue = res.lName;
       
    }
}

window.onload = function () {
    sendAjaxGet("user/this", setDefaults);
}