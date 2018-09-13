function AjaxPost(url,body, func){
	  var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function (){
	    if(this.readyState==4 && this.status == 200){
	      func(this);
	    }
	  };
	  xhr.open("POST",url,true);
	  xhr.setRequestHeader("Accept","application/json");
	  xhr.setRequestHeader("Content-Type","application/json");
	  xhr.send(body);
}
function AjaxGet(url, func){
		  var xhr = new XMLHttpRequest();
		  xhr.onreadystatechange = function (){
		    if(this.readyState==4 && this.status == 200){
		      func(this);
		    }
		  };
		  xhr.open("GET",url,true);
		  xhr.send();
    }
function User(username,password,fname,lname,email,payEmail,address,birthdate){
	var u = {};
	u.username = username;
	u.password = password;
	u.fname = fname;
	u.lname = lname;
	u.email = email;
	u.payEmail = payEmail;
	u.addresses = [address];
	u.birthdate = birthdate;
	console.log(u);
	AjaxPost("newUser",JSON.stringify(u),function(xhr){console.log(xhr.ResponseText)});
}


/* 
 * 
 * 
 */
var genres = [{"name":"Russian Existentialism"},{"name":"Romance"}];
var condition = {"name":"VeryGood"};
var im = {"boodId":1,"imageBlob":null};
AjaxGet("/BookAuction/image/book/1",function(xhr){im = JSON.parse(xhr.responseText);});
var b = {"title":"The Brothers Karamazov","author":"Fyodor Dostoyevsky","Description":"Existential Russian Romance","genres":genres,"bookImages":im,"condition":condition,"publisher":"penguin classics"};

var u = makeUser("EnrenfuchtFraisse","pass");
AjaxGet("/BookAuction/user/22",function(xhr){u = console.log(xhr.reponseText);});

var auction = {"user":u,"book":b,"minimumPrice":20,"buyItNow":45,"createDate":null,"endDate":null};

AjaxPost("/BookAuction/auction/new",JSON.stringify(auction),function(xhr){xhr.responseText;});
