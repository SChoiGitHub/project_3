let error = parent.document.URL.substring(parent.document.URL.indexOf('?error='), parent.document.URL.length);
error = error.slice('?error='.length);

if(error == "invalid"){
	alert("The post you tried to create was invalid. Please check your post.");
}