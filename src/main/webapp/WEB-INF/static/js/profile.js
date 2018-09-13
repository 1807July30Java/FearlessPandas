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
        // var table = document.getElementById("employeeTable");
        // var row = document.createElement("tr");
        // table.appendChild(row);
        // var firstName = document.createElement("td");
        // firstName.innerText = res[i].firstName;
        // var lastName = document.createElement("td");
        // lastName.innerText = res[i].lastName;
        // var userName = document.createElement("td");
        // userName.innerText = res[i].username;
        // var email = document.createElement("td");
        // email.innerText = res[i].email;
        // row.append(firstName, lastName, userName, email);
        var div = document.getElementById("profileInfo");
        var name = document.createElement("h3");
        name.innerText = res.fName + " " + res.lName;
    }
}


window.onload = function () {
    sendAjaxGet("/data?entity=employee&get=managed", populateUser);
};