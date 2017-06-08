function submitLoginForm(){

	validator.initiate(loginValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		$('#loginForm').submit();
	}
	//alert($('#loginForm').attr("action"));
}

function loginOnEnterPress(event)
{
	 var keycode = (event.keyCode ? event.keyCode : (event.which ? event.which : event.charCode));
     //alert(keycode);
     if (keycode == 13) { // keycode for enter key
        // force the 'Enter Key' to implicitly click the Update button
        document.getElementById('loginBtnCntnrWrprDiv').click();
        return false;
     } else  {
        return true;
     }
}

	
