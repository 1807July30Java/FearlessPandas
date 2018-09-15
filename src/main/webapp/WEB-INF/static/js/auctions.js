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

function makeModal(xhr) {
    var res = JSON.parse(xhr.responseText);
    document.getElementById("title").innerText = res.book.title;
    document.getElementById("author").innerText = res.book.author;
    document.getElementById("desc").innerText = res.book.description;
    document.getElementById("publisher").innerText = res.book.publisher;
    document.getElementById("isbn1").innerText = res.book.isbn;
    document.getElementById("condition").innerText = res.book.condition.name;
    var genres = "";
    for (var i = 0; i < res.book.genres.length; i++) {
        if (i != res.book.genres.length - 1) {
            genres += res.book.genres[i].name + ", ";
        } else {
            genres += res.book.genres[i].name;
        }
    }
    document.getElementById("genre").innerText = genres;
    document.getElementById("seller").innerText = res.user.fName + " " + res.user.lName;
    if (res.minimumPrice === 0 && res.buyItNow === 0) {
        document.getElementById("price").innerText = 0;
    } else if (res.minimumPrice > 0) {
        document.getElementById("price").innerText = res.minimumPrice;
    } else {
        document.getElementById("price").innerText = res.buyItNow;
    }
}

function populateAuctions(xhr) {
    if (xhr.responseText) {
    	
        var res = JSON.parse(xhr.responseText);
        var table = document.getElementById("auctionTable");
        for (var i = 0; i < res.length; i++) {
            var row = document.createElement("tr");
            row.setAttribute("id", res[i].auctionId);
            table.appendChild(row);
            var sDate = document.createElement("td");
            var temp = new Date(res[i].createDate);
            sDate.innerText = temp.toLocaleString();
            var eDate = document.createElement("td");
            temp = new Date(res[i].endDate);
            eDate.innerText = temp.toLocaleString();
            var price = document.createElement("td");
            price.innerText = res[i].minimumPrice;
            var bin = document.createElement("td");
            bin.innerText = res[i].buyItNow;
            var book = res[i].book;
            var title = document.createElement("td");
            title.innerText = book.title;
            var author = document.createElement("td");
            author.innerText = book.author;
            var publisher = document.createElement("td");
            publisher.innerText = book.publisher;
            var view = document.createElement("td");
            view.innerHTML = "<button class='btn btn-sm btn-primary btn-block text-uppercase' onclick='sendAjaxGet(\"auction/" + res[i].auctionId + "\", makeModal)' data-toggle='modal' data-target='#exampleModal'>View</button>";
            row.append(sDate, eDate, price, bin, title, author, publisher, view);
        }
    }
}

window.onload = function () {
    sendAjaxGet("auction/this", populateAuctions);
};