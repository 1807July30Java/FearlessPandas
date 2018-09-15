function sendAjaxPost(url, body, func) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this);
        }
    }
        xhr.open("POST", url, true);
        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.send(body);
    
}

function sendAjaxGet(url, func) {
    var xhr = new XMLHttpRequest() || new ActiveXObject("Microsoft.HTTPRequest");
    xhr.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            func(this);
        }
    };
    console.log("xhr ajax call");
    xhr.open("GET", url, true);
    xhr.send();
}

function makeModal(xhr) {
	console.log("xhr modal call");
    var res = JSON.parse(xhr.responseText);
    console.log(res);
    document.getElementById("title1").innerText = res.book.title;
    document.getElementById("author1").innerText = res.book.author;
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

function populate(xhr) {
    var res = JSON.parse(xhr.responseText);
    console.log(res);
    if (xhr.responseText) {
        var jsp = JSON.parse(xhr.responseText);
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
            var bid = document.createElement('a');
            bid.setAttribute('class', 'btn btn-lg btn-primary');
            bid.setAttribute('href', 'bid');
            bid.innerText = "BID?";
            row.append(sDate, eDate, price, bin, title, author, publisher, view,bid);
        }
    }
    
}


function search(){
	var title = document.getElementById("title").value;
	var author = document.getElementById("author").value;
	var publisher = document.getElementById("publisher").value;
	var isbn = document.getElementById("isbn").value;
	var min = document.getElementById("minimum").value;
	var buyNow = document.getElementById("buyNow").value;
	var endDate = document.getElementById("endDate").value;
	var url = {
			"title": title,
			"author": author,
			"publisher": publisher,
			"isbn": isbn,
			"minimumPrice": min,
			"buyNow": buyNow,
			"endDate": endDate		
	}
	
		console.log(url);

	  sendAjaxPost("book/searching", JSON.stringify(url), populate);
	  console.log(url);
}



window.onload = function () {
	document.getElementById("auctionTable").innerText="";
};