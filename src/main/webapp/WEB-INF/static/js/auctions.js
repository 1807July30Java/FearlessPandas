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

function populateAuctions(xhr) {
    if (xhr.responseText) {
        var res = JSON.parse(xhr.responseText);

    }
}

window.onload = function () {
    sendAjaxGet("auction/this", populateAuctions);
}