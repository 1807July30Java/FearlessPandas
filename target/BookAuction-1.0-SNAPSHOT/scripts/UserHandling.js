function AjaxPost(url,body, func){
	  var xhr = new XMLHttpRequest();
	  xhr.onreadystatechange = function (){
	    if(this.readyState==4 && this.status == 200){
	      func(this);
	    }
	  }
	  xhr.open("POST",url,true);
	  xhr.setRequestHeader("Accept","application/json");
	  xhr.setRequestHeader("Content-Type","application/json");
	  xhr.send(body);
	};
	
	function AjaxGet(url, func){
		  var xhr = new XMLHttpRequest();
		  xhr.onreadystatechange = function (){
		    if(this.readyState==4 && this.status == 200){
		      func(this);
		    }
		  }
		  xhr.open("GET",url,true);
		  xhr.send();
		};
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
function makeUser(username,pass){
	var u = {};
	u.username = username;
	u.password = pass;
	u.fname = null;
	u.lname = null;
	u.email = null;
	u.payEmail = null;
	u.addresses = [{}];
	return u;
}