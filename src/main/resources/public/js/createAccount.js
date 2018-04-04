function generateDates() {
    var today = new Date();
    var year = today.getFullYear();

    var date_element = document.getElementById("Date");
    for (var i = 1; i <= 31; i++) {
	date_element.innerHTML += "<option value=\"" + i + "\">" + i + "</option>\n";
    }

    var year_element = document.getElementById("Year");
    for (var i = (year - 100); i <= year; i++) {
	year_element.innerHTML += '<option value=\"' + i + '\">' + i + '</option>\n';
    }
}

function formValidate() {
    var errstatus = textBoxValidate() + dateValidate() + passwordValidate() + securityValidate();
    if (errstatus) {
	alert("Some parts of the form missing or invalid; please check form");
	return (false);
    }
}

function textBoxValidate() {
    var textboxes = document.querySelectorAll('input[type="text"]');
    var errflag = 0;

    for (var i = 0; i < textboxes.length; i++) {
	if (textboxes[i].value == '') {
	    document.getElementById('tb' + i + 'alert').style.display = "inline";
	    errflag = 1;
	}
	else {
	    document.getElementById('tb' + i + 'alert').style.display = "none";
	}
    }

    return(errflag);
}    

function dateValidate() {
    var month = document.getElementById('Month').value;
    var date = document.getElementById('Date').value;
    var datealert = document.getElementById('datealert');
    var invalid_date = (month == 2 && date > 28) || (month % 2 == 0 && month <= 7 && date > 30) || (month % 2 == 1 && month > 7 && date > 30);

    if (invalid_date) {
	datealert.style.display = 'inline';
	return (1);
    }
    else {
	datealert.style.display = 'none';
	return (0);
    }
}

function passwordValidate() {
    var password = document.querySelectorAll('input[type="password"]')[0].value;
    var cpassword = document.querySelectorAll('input[type="password"]')[1].value;
    var errflag = 0;
    if (password.length < 8) {
	errflag = 1;
	document.getElementById('pwordalert').style.display = 'inline';
    }
    else {
	document.getElementById('pwordalert').style.display = 'none';
    }
    if (cpassword != password) {
	errflag = 1;
	document.getElementById('cpwordalert').style.display = 'inline';
    }
    else {
	document.getElementById('cpwordalert').style.display = 'none';
    }

    return (errflag);
}

function securityValidate() {
    var sq1 = document.getElementById("sq1").value;
    var sq2 = document.getElementById("sq2").value;
    var sq3 = document.getElementById("sq3").value;
    var sq1alert = document.getElementById("sq1alert");
    var sq2alert = document.getElementById("sq2alert");
    var sq3alert = document.getElementById("sq3alert");
    var errflag = 0;

    if (sq1 == sq2 && sq1 == sq3) {
	sq1alert.style.display = "inline";
	sq2alert.style.display = "inline";
	sq3alert.style.display = "inline";
	errflag = 1;
    }
    else if (sq1 == sq2) {
	sq1alert.style.display = "inline";
	sq2alert.style.display = "inline";
	sq3alert.style.dsipaly = "none";
	errflag = 1;
    }
    else if (sq1 == sq3) {
	sq1alert.style.display = "inline";
	sq2alert.style.display = "none";
	sq3alert.style.display = "inline";
	errflag = 1;
    }
    else if (sq2 == sq3) {
	sq1alert.style.display = "none";
	sq2alert.style.display = "inline";
	sq3alert.style.display = "inline";
	errflag = 1;
    }
    else {
	sq1alert.style.display = "none";
	sq2alert.style.display = "none";
	sq3alert.style.display = "none";
    }

    return (errflag);
}