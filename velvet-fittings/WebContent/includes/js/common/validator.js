var validator = {};
validator.isTokenToBeReplaced = true;
validator.failedElemsExist = false;
validator.failedElems = {};
validator.validationMode = "Single"; // allowable options being 'Single' and 'Batch'. 'Single' being the default
validator.displayMode = "Header"; // allowable options being 'Header' and 'Bubble'. 'Header' being the default
validator.errorMsgDiv = "errMsgDiv";
validator.errorMsgDivStyle = "right-pointer-wrapper";
validator.errorMsgDivPattern = "errMsgCntnr_";
validator.messages = {

       require:"#{field} is required.",
       // Format validators:
       match:"#{field} is in an invalid format.",
       integer:"#{field} must be a positive, whole number.",
       date:"#{field} must be formatted as a date. (yyyy-mm-dd)",
       time24:"#{field} must be formatted as a 24 Hr. format time. (hh:mm)",
       email:"#{field} must be formatted as an email.",
       ericssonemail:"#{field} must be formatted as abc@ericsson.com.",
       usd:"#{field} must be formatted as a US Dollar amount.",
       url:"#{field} must be formatted as a URL.",
       number:"#{field} must be a number.",
       currencynumber:"#{field} must be a formatted as currency number.",
       signum:"#{field} must start with e and should have 7 letters.",
       charactercheck:"#{field} contains invalid characters.",
       alphabetOnly:"#{field} must contain only alphabets.",
       double_two:"#{field} should be a number with format ####.##",
       excelFile:"Please upload a valid excel file."
};

validator.patterns = {
   integer:/^\d+$/,
   
   // Used to use Date.parse(), 
   // where the function would accept 09/08/2009 as parseable.
   date:/^\d{1,4}\-((0\d)|(1[012]))\-([012]\d|30|31)$/, 
   
   email:/^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/i,
   ericssonemail:/^[a-zA-Z0-9._-]+@ericsson.com$/i,
   usd:/^\$?((\d{1,3}(,\d{3})*)|\d+)(\.(\d{2})?)?$/,            
   url:/^(https?|ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(\#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i,
   
   // Number should accept floats or integers, be they positive or negative.
   // It should also support scientific-notation, written as a lower or capital 'E' followed by the radix.
   // Number assumes base 10. 
   // Unlike the native parseFloat or parseInt functions, this should not accept trailing Latin characters.
   number:/^[+-]?(\d+(\.\d*)?|\.\d+)([Ee]-?\d+)?$/,
   //currencynumber:/^[+-]?((\d(,\d)*)|\d+)(\.(\d{2})?)?$/,
   currencynumber:/^[+-]?((\d{1,3}(,\d{3})*)|\d+)(\.(\d{2})?)?$/,
   
   zip:/^\d{5}(-\d{4})?$/,
   phone:/^[2-9]\d{2}-\d{3}-\d{4}$/,
   guid:/^(\{?([0-9a-fA-F]){8}-(([0-9a-fA-F]){4}-){3}([0-9a-fA-F]){12}\}?)$/,
   time12:/^((0?\d)|(1[12])):[0-5]\d?\s?[aApP]\.?[mM]\.?$/,
   
   //time24:/^(20|21|22|23|[01]\d|\d)(([:][0-5]\d){1,2})$/,
   time24:/^(20|21|22|23|[01]\d)[:]([0-5]\d)$/,
   
   nonHtml:/^[^<>]*$/,
   signum:/^[eE][a-zA-Z]{6}$/,
   alphabetOnly:/^[a-zA-Z ]*$/,
   double_two:/^\d{1,4}(\.\d{0,2})?$/,
   excelFile:/^[ a-zA-Z0-9._-]+.xls$/
};

function getErrorMessageForElement(requiredValidationObj, messageType){
	var requiredElemId = requiredValidationObj.id;
	var requiredElemMsg = requiredValidationObj.msg;
	var requiredElemTokenReplacement = requiredValidationObj.isTokenToBeReplaced;
	var requiredElem = $("#"+requiredElemId);

	var errmsg = requiredElemMsg ? requiredElemMsg : validator.messages[messageType];
	var isTokenToBeReplaced = requiredElemTokenReplacement ? requiredElemTokenReplacement : validator.isTokenToBeReplaced;
	if(errmsg && isTokenToBeReplaced && requiredElem.attr("validatorLabel")){
		errmsg = errmsg.replace(new RegExp("#\\{" + "field" + "\\}", "g"), requiredElem.attr("validatorLabel"));
	}
	return errmsg;
}

function regExpTestWrapper(validationExpression,paramValue){
	
	var wholePatternMatched = false;
	var validationExpRegex = new RegExp(validationExpression);
	wholePatternMatched = validationExpRegex.test(paramValue);
	
	return wholePatternMatched;
}

/*validator.changePageStateForError = function(validationObj, failedElemKey, messageType){
	var errmsg = getErrorMessageForElement(validationObj, messageType);
	validator.failedElems[failedElemKey] = errmsg;
	var currErrMsg = $("#"+validator.errorMsgDiv).html();
	if(currErrMsg.length>0){
		currErrMsg = currErrMsg + "<br/>";
	}
	currErrMsg = currErrMsg + errmsg;
	$("#"+validator.errorMsgDiv).html(currErrMsg);
	validator.failedElemsExist = true;
};*/

validator.changePageStateForError = function(validationObj, failedElemKey, messageType, isElemRadioOrCheckbox){
	var errmsg = getErrorMessageForElement(validationObj, messageType);
	validator.failedElems[failedElemKey] = errmsg;
	
	if(validator.displayMode =="Header"){
		var currErrMsg = $("#"+validator.errorMsgDiv).html();
		if(currErrMsg.length>0){
			currErrMsg = currErrMsg + "<br/>";
		}
		currErrMsg = currErrMsg + errmsg;
		$("#"+validator.errorMsgDiv).removeClass("successMsg");
		$("#"+validator.errorMsgDiv).removeClass("errorMsg");
		$("#"+validator.errorMsgDiv).addClass("validationMsg");
		$("#"+validator.errorMsgDiv).html(currErrMsg);
		$("#"+validator.errorMsgDiv).show();
	}else if(validator.displayMode =="Bubble"){
		var failedElem;
		if(isElemRadioOrCheckbox){
			var noOfElems = $("[name='"+failedElemKey+"']").length;
			failedElem = $("[name='"+failedElemKey+"']:eq("+(noOfElems-1)+")");
		}else{
			failedElem = $("#"+validationObj.id);
		}
		
		if($("#"+validator.errorMsgDivPattern+failedElemKey).length==0){
			//var floaterErrDivHtml = "<span class='"+validator.errorMsgDivStyle+"' id='"+validator.errorMsgDivPattern+failedElemKey+"'></span>";
			var floaterErrDivHtml = "<span class='"+validator.errorMsgDivStyle+"'><span class='right-pointer'></span><span class='msgSpan' id='"+validator.errorMsgDivPattern+failedElemKey+"'></span></span>";
			failedElem.after(floaterErrDivHtml);
			
		}
		$("#"+validator.errorMsgDivPattern+failedElemKey).html(errmsg);
		$("#"+validator.errorMsgDivPattern+failedElemKey).show();
	}
	
	validator.failedElemsExist = true;
};



validator.addRequiredValidationEntry = function(validationObjToAdd){
	var requiredValidationsArr = validator.validations.requiredValidations;
	requiredValidationsArr.push(validationObjToAdd);
};

validator.addFormatValidationEntry = function(validationObjToAdd){
	var formatValidationsArr = validator.validations.formatValidations;
	formatValidationsArr.push(validationObjToAdd);
};

validator.addDataTypeValidationEntry = function(validationObjToAdd){
	var dataTypeValidationsArr = validator.validations.dataTypeValidations;
	dataTypeValidationsArr.push(validationObjToAdd);
};

validator.requiredValidator = function(){
	var requiredValidationsArr = validator.validations.requiredValidations;
	for(var k=0;k<requiredValidationsArr.length;k++){
		if(validator.validationMode=="Single" && validator.failedElemsExist){
			break;
		}
		var requiredValidationObj = requiredValidationsArr[k];
		if(requiredValidationObj){
			var requiredElemId = requiredValidationObj.id;
			//var requiredElemMsg = requiredValidationObj.msg;
			
			var requiredElem = $("#"+requiredElemId);
			if(requiredElem){
				if(requiredElem.get(0).tagName.toUpperCase()=="SELECT"){
					if(!validator.failedElems[requiredElemId]){
						if(!requiredElem.attr("multiple")){
							if(requiredElem.val()=="-1"){
								validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
							}
						}else{
							if(requiredElem.val() == null){
								validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
							}else if(requiredElem.val().length==0){
									validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
							}
						}
					}
				}else if(requiredElem.is(":text")){
					if(!validator.failedElems[requiredElemId]){
						if($.trim(requiredElem.val()).length==0){
							validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
						}
					}
				}else if(requiredElem.get(0).tagName.toUpperCase()=="TEXTAREA"){
					if(!validator.failedElems[requiredElemId]){
						if($.trim(requiredElem.val()).length==0){
							validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
						}
					}
				}else if(requiredElem.is(":checkbox")){
					var isValueEntered = false;
					var requiredElemName = requiredElem.attr("name");
					if(!validator.failedElems[requiredElemName]){
						var requiredCheckBoxes = $(":checkbox[name='"+requiredElemName+"']");
						
						for(var requiredCheckBoxesCounter=0;requiredCheckBoxesCounter<requiredCheckBoxes.length;requiredCheckBoxesCounter++){
							var requiredCheckBox = requiredCheckBoxes.get(requiredCheckBoxesCounter);
							var checkBoxToValidate = $("#"+requiredCheckBox.id);
							
							if(checkBoxToValidate.attr("checked")){
								isValueEntered = true;
								break;
							}
						}
						if(!isValueEntered){
							validator.changePageStateForError(requiredValidationObj, requiredElemName, "require", true);
						}
					}
				}else if(requiredElem.is(":radio")){
					var isValueEntered = false;
					var requiredElemName = requiredElem.attr("name");
					if(!validator.failedElems[requiredElemName]){
						var requiredRadios = $(":radio[name='"+requiredElemName+"']");
						
						for(var requiredRadiosCounter=0;requiredRadiosCounter<requiredRadios.length;requiredRadiosCounter++){
							var requiredRadio = requiredRadios.get(requiredRadiosCounter);
							var radioToValidate = $("#"+requiredRadio.id);
							
							if(radioToValidate.attr("checked")){
								isValueEntered = true;
								break;
							}
						}
						if(!isValueEntered){
							validator.changePageStateForError(requiredValidationObj, requiredElemName, "require", true);
							
						}
					}
				}else if(requiredElem.is(":password")){
					if(!validator.failedElems[requiredElemId]){
						if($.trim(requiredElem.val()).length==0){
							validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
						}
					}
				}else if(requiredElem.is(":file")){
					if(!validator.failedElems[requiredElemId]){
						if($.trim(requiredElem.val()).length==0){
							validator.changePageStateForError(requiredValidationObj, requiredElemId, "require");
						}
					}
				}
			}
		}
	}
};


validator.formatValidator = function(){
	var formatValidationsArr = validator.validations.formatValidations;
	for(var k=0;k<formatValidationsArr.length;k++){
		if(validator.validationMode=="Single" && validator.failedElemsExist){
			break;
		}
		var formatValidationObj = formatValidationsArr[k];
		var formatValidationElemId = formatValidationObj.id;
		//var formatValidationElemMsg = formatValidationObj.msg;
		var formatValidationType = formatValidationObj.format;
		
		var formatValidationElem = $("#"+formatValidationElemId);
		if(formatValidationElem && formatValidationElem.val().length>0){
			if(!validator.failedElems[formatValidationElemId]){
				formatValidationExpression = validator.patterns[formatValidationType];
				formatValidationElemVal = formatValidationElem.val();
				if(formatValidationExpression && formatValidationElemVal.length>0){
					var patternMatchFound = regExpTestWrapper(formatValidationExpression,formatValidationElemVal);
					if(!patternMatchFound){
						validator.changePageStateForError(formatValidationObj, formatValidationElemId, formatValidationType);
					}
				}
			}
		}
	}
};

validator.dataTypeValidator = function(){
	var dataTypeValidationsArr = validator.validations.dataTypeValidations;
	for(var k=0;k<dataTypeValidationsArr.length;k++){
		if(validator.validationMode=="Single" && validator.failedElemsExist){
			break;
		}
		var dataTypeValidationObj = dataTypeValidationsArr[k];
		var dataTypeValidationElemId = dataTypeValidationObj.id;
		//var formatValidationElemMsg = formatValidationObj.msg;
		var dataTypeValidationChecker = dataTypeValidationObj.checker;
		var dataTypeValidationMsgType = dataTypeValidationObj.msgType;
		
		var dataTypeValidationElem = $("#"+dataTypeValidationElemId);
		if(dataTypeValidationElem && dataTypeValidationElem.val().length>0){
			if(!validator.failedElems[dataTypeValidationElemId]){
				
				if(typeof dataTypeValidationChecker == "function"){				 
					var dataTypeValidationCheckerStts = dataTypeValidationChecker();
					if(!dataTypeValidationCheckerStts){
						validator.changePageStateForError(dataTypeValidationObj, dataTypeValidationElemId, dataTypeValidationMsgType);
					}
				}
			}
		}
	}
};

validator.customValidator = function(){
	var customValidationsArr = validator.validations.customValidations;
	if(customValidationsArr && customValidationsArr.length>0){
		for(var k=0;k<customValidationsArr.length;k++){
			if(validator.validationMode=="Single" && validator.failedElemsExist){
				break;
			}
			var customValidationObj = customValidationsArr[k];
			var customValidationElemId = customValidationObj.id;
			//var formatValidationElemMsg = formatValidationObj.msg;
			var customValidationChecker = customValidationObj.checker;
			var customValidationMsgType = customValidationObj.msgType;
			
			var customValidationElem = $("#"+customValidationElemId);
			if(customValidationElem){
				if(!validator.failedElems[customValidationElemId]){
					
					if(typeof customValidationChecker == "function"){				 
						var customValidationCheckerStts = customValidationChecker();
						if(!customValidationCheckerStts){
							validator.changePageStateForError(customValidationObj, customValidationElemId, customValidationMsgType);
						}
					}
				}
			}
		}
	}
};

validator.initiate = function(validationsObj){
	validator.reset();
	initiateValidator(validationsObj);
	//validator.validations = validationsObj;
};

function initiateValidator(validationObj){
	validator.validations = {};
	
	validator.validations.requiredValidations = [];
	if(validationObj.requiredValidations && validationObj.requiredValidations.length>0){
		for(var k=0;k<validationObj.requiredValidations.length;k++){
			validator.validations.requiredValidations.push(validationObj.requiredValidations[k]);
		}
	}
	
	validator.validations.formatValidations = [];
	if(validationObj.formatValidations && validationObj.formatValidations.length>0){
		for(var k=0;k<validationObj.formatValidations.length;k++){
			validator.validations.formatValidations.push(validationObj.formatValidations[k]);
		}
	}
	
	validator.validations.dataTypeValidations = [];
	if(validationObj.dataTypeValidations && validationObj.dataTypeValidations.length>0){
		for(var k=0;k<validationObj.dataTypeValidations.length;k++){
			validator.validations.dataTypeValidations.push(validationObj.dataTypeValidations[k]);
		}
	}
	
	validator.validations.customValidations = [];
	if(validationObj.customValidations && validationObj.customValidations.length>0){
		for(var k=0;k<validationObj.customValidations.length;k++){
			validator.validations.customValidations.push(validationObj.customValidations[k]);
		}
	}
}

validator.reset = function(){
	validator.failedElems = {};
	validator.failedElemsExist = false;
	$("#"+validator.errorMsgDiv).html("");
	$("."+validator.errorMsgDivStyle).remove();
};

validator.validate = function(options){
	validator.reset();
	if(options){
		validator = $.extend(validator, options);
	}
	validator.requiredValidator();
	validator.formatValidator();
	validator.dataTypeValidator();
	validator.customValidator();
	if (validator.failedElemsExist) {
		$("html, body").animate({ scrollTop: 0 }, "slow");
	}	
};

validator.validatewithoutscroll = function(options){
	validator.reset();
	if(options){
		validator = $.extend(validator, options);
	}
	validator.requiredValidator();
	validator.formatValidator();
	validator.dataTypeValidator();
	validator.customValidator();
	if (validator.failedElemsExist) {
		//$("html").scrollTop(0);
	}	
};
