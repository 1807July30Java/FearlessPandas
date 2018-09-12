/**
 * 
 */

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

AjaxGet("/BookAuction/user/22",function(xhr){console.log(xhr.responseText);});