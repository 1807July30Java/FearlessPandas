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
        // console.log(res);
        // console.log(res.fName);
        if (res.fName) {

        	console.log(res.fName);
			document.getElementById("firstName").innerText = 
					 res.fName;}
        if (res.lName) {
        	console.log(res.fName);
        	document.getElementById("lastName").innerText = 
        		res.lName;}

        if (res.username) {
            console.log(res.username);
            document.getElementById("username").innerText =
                res.username;
        }
        if (res.email) {
            console.log(res.username);
            document.getElementById("email").innerText =
                res.email;
        }
        if (res.payEmail) {
            console.log(res.username);
            document.getElementById("payEmail").innerText =
                res.payEmail;
        }
    }
}


window.onload = function () {
    sendAjaxGet("user/this", populateUser);
};