/**
 *
 */

function AjaxGet(url, func) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            func(this);
        }
    }
    xhr.open("GET", url, true);
    xhr.send();
};

function AjaxPost(url, body, func) {
    var xhr = new XMLHttpRequest();
    xhr.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            func(this);
        }
    }
    xhr.open("POST", url, true);
    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    xhr.send(body);
};

function makeUser(username, pass) {
    var u = {};
    u.username = username;
    u.password = pass;
    u.fname = "Teststein";
    u.lname = "Testerberg";
    u.email = "TestyT@Testmail.com";
    u.payEmail = "TestyT@Testmail.com";
    u.addresses = [{"street": "Example Blvd", "city": "Testerton", "apartment": "T35t", "state": "TT", "zip": 06091}];
    AjaxPost("/BookAuction/user/new", JSON.stringify(u), function (xhr) {
        console.log(xhr.responseText);
    });
    return u;
}

function makeBook(title, author, publisher, condition) {
    var b = {};
    b.title = title;
    b.author = author;
    b.publisher = publisher;
    b.description = "a book";
    b.condition = {"name": condition};
    b.Genres = [{"name": "Genre a"}, {"name": "Genre b"}];
    return b;

}

function makeAuctions(u) {
    var auctions = [];
    console.log("Auction owner:" + JSON.stringify(u));
    for (var i = 0; i < 10; i++) {
        auctions.push({
            "user": u,
            "book": makeBook("title" + i, "author" + i, "publisher" + i, "GOOD"),
            "minimumPrice": 20,
            "buyItNow": 45,
            "createDate": new Date().getTime(),
            "endDate": new Date().getTime()
        });
    }
    return auctions;
}

makeUser("Landon", "pass");
makeUser("Jeremy", "pass");
makeUser("Omar", "pass");
makeUser("Alpha", "pass");
var genres = [{"name": "Russian Existentialism"}, {"name": "Romance"}];
var condition = {"name": "VeryGood"};
var im = {"boodId": 1, "imageBlob": null};
AjaxGet("/BookAuction/image/book/1", function (xhr) {
    im = xhr.responseText;
});
var u = {};
AjaxGet("/BookAuction/user/username/OPpandas", function (xhr) {
    u = JSON.parse(xhr.responseText);
});


var auctions = makeAuctions(u);

auctions.forEach(function (element) {
    if (element.user != null) {
        AjaxPost("/BookAuction/auction/new", JSON.stringify(element), function (xhr) {
            console.log(xhr.responseText);
        });
    }
})
