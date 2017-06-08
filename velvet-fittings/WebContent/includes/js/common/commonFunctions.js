/*-
 * @(#)commonFunctions.js        1.0 Mar 29, 2012
 * @(#)EriPro
 * Copyright (c) 2012 EGIL.
 * All rights reserved.
 *  
  Revision History
 * 
 * Revision   Date          Author        Description
 * 			
 * ======================================================================================
 * 1.1         17th July		epasoum		Rel 4.0		Eripro_CR_18	NIDs within a project must be unique i.e. 2 or more entries 
 * 1.2			25th July,2012	edebaba		Rel 4.0/Eripro_CR_19/PIID Tab and Menu         														for NID within a project cannot be similar(create,edit,approve project)
 * 1.3 Changed for Release 4.1,    18-08-2012   eadighu  Eripro_CR_6;   Program Start Date and Program End Date added 		
 * 1.4			25th July,2012	edebaba		Rel 4.0/Eripro_CR_19/PIID Tab and Menu         
 * 1.5			22nd Aug,2012	epasoum		Rel 5.0/Create Project Budget [Added sortOptionsValue for value based sorting]														for NID within a project cannot be similar(create,edit,approve project)
 * 1.6			03/09/12 		edebaba		Rel5.0/showDiv method introduced.(Overloaded)
 */

var bodyJSON = {};
var timeoutJSON={};
timeoutJSON.sessionTimeout=1780000;
timeoutJSON.tmtImpltrId="";

function getBrowserName()
{ 
	var browserName = ""; 
	var ua = navigator.userAgent.toLowerCase();

	if ( ua.indexOf( "opera" ) != -1 ){ browserName = "opera";}
	else if ( ua.indexOf( "chrome" ) != -1 ) { browserName = "chrome"; }
	else if ( ua.indexOf( "msie" ) != -1 ) { browserName = "msie"; } 
	else if ( ua.indexOf( "safari" ) != -1 ) { browserName = "safari"; 	} 
	else if ( ua.indexOf( "mozilla" ) != -1 ) { if ( ua.indexOf( "firefox" ) != -1 ) { browserName = "firefox"; } else { browserName = "mozilla"; } } 
	return browserName; 
}

function isInternetExplorer(){
	var ret = false;
	var browserName = getBrowserName();
	if (browserName=="msie"){
		ret = true;
	}
	return ret;
}

function isInternetExplorer6(){
	var ret = false;
	var version = parseFloat(navigator.appVersion.split("MSIE")[1]);  
	if(version==6){
		ret = true;
	}
	return ret;
}

var browserName = getBrowserName();

function get_viewport_width(){
	var viewportwidth = 0;
	if (typeof window.innerWidth!='undefined'){viewportwidth=window.innerWidth;}
	else if (typeof document.documentElement!='undefined' && typeof document.documentElement.clientWidth!='undefined' && document.documentElement.clientWidth!= 0){
		viewportwidth=document.documentElement.clientWidth; 
	}else{viewportwidth=document.getElementsByTagName('body')[0].clientWidth;}
	return viewportwidth;
}

function get_viewport_height(){
		var viewportheight = 0; 
		if (typeof window.innerWidth!='undefined'){viewportheight=window.innerHeight;}
		else if (typeof document.documentElement!='undefined' && typeof document.documentElement.clientWidth!='undefined' && document.documentElement.clientWidth!= 0){
			viewportheight=document.documentElement.clientHeight;
		}else{viewportheight=document.getElementsByTagName('body')[0].clientHeight;}
		return viewportheight;
}



function hasValue(elem,value){
	
	var arraysize = elem.options.length;
	for(var i=0;i < arraysize;i++){
		if(elem.options[i].value == value){
			return true;
		}
	}
	return false;
}

function compareOptionText(a,b) {  
	return a.text!=b.text ? a.text<b.text ? -1 : 1 : 0;  
} 

function sortOptions(list) {  
	var items = list.options.length;  
	////alert(items);
	// create array and make copies of options in list  
	var tmpArray = new Array(items);  
	for (var i=0; i<items; i++ )    
	tmpArray[i] = new Option(list.options[i].text,list.options[i].value);  
	// sort options using given function  
	tmpArray.sort(compareOptionText);  
	// make copies of sorted options back to list  
	for ( var i=0; i<items; i++ ){   
		list.options[i] = new Option(tmpArray[i].text,tmpArray[i].value); 
	}
}
function compareOptionValue(a,b) {  
	return a.value!=b.value ? a.value<b.value ? -1 : 1 : 0;  
}
function sortOptionsValue(listId) {  
	var list = document.getElementById(listId);
	var items = list.options.length;  
	////alert(items);
	// create array and make copies of options in list  
	var tmpArray = new Array(items);  
	for (var i=0; i<items; i++ )    
	tmpArray[i] = new Option(list.options[i].text,list.options[i].value);  
	// sort options using given function  
	tmpArray.sort(compareOptionValue);  
	// make copies of sorted options back to list  
	for ( var i=0; i<items; i++ ){   
		list.options[i] = new Option(tmpArray[i].text,tmpArray[i].value); 
	}
}



function selectMultiSelect(leftElementId, rightElementId){
	var leftElement = document.getElementById(leftElementId);
	var rightElement = document.getElementById(rightElementId);
	var arrayLength = leftElement.options.length;
	for(var i=arrayLength-1;i > -1;i--){
		
		if(leftElement.options[i] && leftElement.options[i].selected){
			if(!hasValue(rightElement,leftElement.options[i].value)){
				var opt = document.createElement("option"); 
				rightElement.options.add(opt);
				opt.value = leftElement.options[i].value;
				opt.text = leftElement.options[i].text;
				leftElement.remove(i);
				i++; 
				//arrayLength--;
			}
		}
	}
	
	sortOptions(leftElement);
	sortOptions(rightElement);
}

function selectAllMultiSelect(leftElementId, rightElementId){
	var leftElement = document.getElementById(leftElementId);
	var rightElement = document.getElementById(rightElementId);
	var arrayLength = leftElement.options.length;
	for(var i=arrayLength-1;i > -1;i--){
		
		if(leftElement.options[i]){
			if(!hasValue(rightElement,leftElement.options[i].value)){
				var opt = document.createElement("option"); 
				rightElement.options.add(opt);
				opt.value = leftElement.options[i].value;
				opt.text = leftElement.options[i].text;
				leftElement.remove(i);
				i++; 
				//arrayLength--;
			}
		}
	}
	
	sortOptions(leftElement);
	sortOptions(rightElement);
}

function removeMultiSelect(leftElementId, rightElementId){
	var rightElement = document.getElementById(leftElementId);
	var leftElement = document.getElementById(rightElementId);
	var arrayLength = rightElement.options.length;
	for(var i=0;i < arrayLength;i++){
		if(rightElement.options[i] && rightElement.options[i].selected){
			var opt = document.createElement("option");
			leftElement.options.add(opt);
			opt.value = rightElement.options[i].value;
			opt.text = rightElement.options[i].text;
			rightElement.remove(i);
			i--;
			arrayLength--;  
		//		return;
		}
	}
	sortOptions(leftElement);
	sortOptions(rightElement);
}

function removeAllMultiSelect(leftElementId, rightElementId){
	var rightElement = document.getElementById(leftElementId);
	var leftElement = document.getElementById(rightElementId);
	var arrayLength = rightElement.options.length;
	for(var i=0;i < arrayLength;i++){
		if(rightElement.options[i]){
			var opt = document.createElement("option");
			leftElement.options.add(opt);
			opt.value = rightElement.options[i].value;
			opt.text = rightElement.options[i].text;
			rightElement.remove(i);
			i--;
			arrayLength--;  
		//		return;
		}
	}
	sortOptions(leftElement);
	sortOptions(rightElement);
}


function homePageOnloadHandler(){
	$("#eriproHomeSlider").easySlider({
		prevId: "eriproHomePrevBtn",
		nextId: "eriproHomeNextBtn",
		auto: true, 
		continuous: true
	});
	
}



function landingPageOnloadHandler(){
	
}


function showStaticOvrl(ovrlWrprId){
	$("#trsprtOvrlId").html($("#"+ovrlWrprId).html());
	$("#"+ovrlWrprId).html("");
	window.scroll(0,0);
	$("#trsprtOvrlId").show();
	document.documentElement.style.overflow = "hidden";
}

function hideStaticOvrl(ovrlWrprId){
	$("#trsprtOvrlId").hide();
	$("#"+ovrlWrprId).html($("#trsprtOvrlId").html());
	$("#trsprtOvrlId").html("");
	$("#ovrlMsgCntnrId").hide();
	document.documentElement.style.overflow = "auto";
	document.body.style.marginRight='0px';
}

function addNewNetworkID(){
	var addedNetworkIDsIndex = parseInt($("#visibleNetworkIDs").val(),10)+1;
	////alert(visibleAddedIssuesIndex);
	var individualNetworkIDTemplateHTML = $("#crtPrjNetworkIDTemplateWrpr").html();
	var individualNetworkIDHTML = individualNetworkIDTemplateHTML.replace(new RegExp("SECTIONINDEX","g"), addedNetworkIDsIndex) ;
	////alert(individualIssueHTML);
	$("#inrPgBdNetworkIDWrprId").append(individualNetworkIDHTML);
	$("#visibleNetworkIDs").val(addedNetworkIDsIndex);
	
}

function editNewNetworkID(){
	var addedNetworkIDsIndex = parseInt($("#visibleNetworkIDs").val(),10)+1;
	var individualNetworkIDTemplateHTML = $("#crtPrjNetworkIDTemplateWrpr").html();
	var individualNetworkIDHTML = individualNetworkIDTemplateHTML.replace(new RegExp("networkIdListNameSECTIONINDEX","g"), "projectBean.networkIdList[SECTIONINDEX]") ;
	var NetworkIDHTML = individualNetworkIDHTML.replace(new RegExp("SECTIONINDEX","g"), addedNetworkIDsIndex) ;
	$("#inrPgBdNetworkIDWrprId").append(NetworkIDHTML);
	$("#visibleNetworkIDs").val(addedNetworkIDsIndex);

}

function deleteNetworkID(networkIDIndex){
	var networkIDIndexToDelete = parseInt(networkIDIndex,10);
		////alert(addedIssuesIndex);
		$("#crtPrjNetworkIDWrpr_"+networkIDIndexToDelete).hide();
		$("#crtPrjNetworkIDWrpr_"+networkIDIndexToDelete).html("");
		$("#crtPrjNetworkIDWrpr_"+networkIDIndexToDelete).attr("id", "deletedItem");
		var updatedNetworkIDsIndex = networkIDIndexToDelete-1;
		////alert(updatedIssuesIndex);
		$("#visibleNetworkIDs").val(updatedNetworkIDsIndex);
}

function showVerticalDetails(){
	$("#inrPgBdCntntVerticalDetails").show();
	$("#addVerticalBtnCntnrId").hide();
}

function saveVerticalDetails(){
	$("#inrPgBdCntntVerticalDetails").hide();
	$("#addVerticalBtnCntnrId").show();
}

function openLandingPage(){
	window.location.href="landingpage.html";
}


function fetchRolesForUser(){
	
	$.ajax({
		type: 'POST',
	    url: "ajaxSpring.do",
	    timeout: 5000,
	    async : false,
	    data : {
	          name:"test",
	          key:"foo",
	          key2:"bar",
	          key3:stringifiedjsonData
	       },
	    success: function(data, textStatus ){
	       //alert('request successful :::\n'+data);
	       var loginCredentials = JSON.parse(data);
	       var statusCode = loginCredentials.statusCode;
		   if(statusCode=="Y"){
				var roleListJSONArrStr = loginCredentials.roleList;
				populateDropdown("userRole", roleListJSONArrStr);
				$("#loginCredentialsCntnrId").hide();
				$("#loginRoleCntnrId").show();
		   }else{
			   
		   }
	    },
	    error: function(xhr, textStatus, errorThrown){
	       //alert('request failed :'+errorThrown);
	    }
	  });
	
}


function handlePageOnloadFunctions(){
	//alert("in handlePageOnloadFunctions");
	//alert($("#PAGE_IDENTIFIER").val());
	if($("#PAGE_IDENTIFIER").val()=="ORDER_REPORT"){
		//alert("in here.....");
		$("#orderSearchBeanOrderFromDate,#orderSearchBeanOrderToDate").datepicker({
			showOn : "button",
			buttonImage : "../includes/images/cal.gif",
			buttonImageOnly : true,
			dateFormat : "yy-mm-dd"
		});
	}
}

function showPgBdCntnr(){
	if($("#SESSION_EXPIRY_IDENTIFIER").val()=="Y"){
		if($("#SSO_ENABLED_FLAG").val()=="Y"){
			window.location.href = $("#SESSION_EXPIRY_REDIRECT_URL").val();
		}else{
			window.location.href = $("#CONTEXT_FULL_PATH").val();
		}
	}else{
	    initCalendar();
	    handlePageOnloadFunctions();
		
	    $("select, textarea, input").each(function(){
			if($(this).attr("title") && $(this).attr("title").length>0){
				$(this).attr("validatorLabel", $(this).attr("title"));
			}
		});
	   	HighlightListTooltip();
	   
		$("#pgBdThrbrCntnrId").hide();
		$("#inrPgBdTextCntnrId").show();
		initiateTimeout();
	}
	
}

function initiateTimeout(){
	/*if(timeoutJSON.tmtImpltrId.length>0){
		clearTimeout(timeoutJSON.tmtImpltrId);
	}
	timeoutJSON.tmtImpltrId = setTimeout("timeoutUser()", timeoutJSON.sessionTimeout)+"";*/
}

function submitPgBdForm(formId, formAction, onSuccessCallBack){
	$("#inrPgBdTextCntnrId").hide();
	$("#pgBdThrbrCntnrId").show();
	$("#"+formId).attr("action", formAction);
	$("#"+formId).ajaxForm({
		target : '#inrPgBdTextCntnrId',
		success : onSuccessCallBack.success,
		error : function() {
			//alert("Error");
		}
	});
	//alert($("#"+formId).attr("action"));
	$("#"+formId).submit();
}

function hideOvrl(contentType){
	//$(".trsprtInr").hide();
	$("#trsprtOvrlId").hide();
	$("#trsprtOvrlId").html("");
	//$(".trsprtInr").hide();

	document.documentElement.style.overflow = "auto";
	document.body.style.marginRight='0px';
}

function cloneData(sourceId, targetId){
	var mapperJSON = {};
	$("#"+sourceId+" select"+" , "+"#"+sourceId+" textarea").each(function(){
		if($(this).attr("id") && $(this).attr("id").length>0){
			mapperJSON[$(this).attr("id")] = $(this).val();
		}
		////alert($(this).attr("id")+"::::::"+$(this).val());	
	});
	
	$("#"+targetId+" select"+" , "+"#"+targetId+" textarea").each(function(){
		if($(this).attr("id") && $(this).attr("id").length>0){
			$(this).val(mapperJSON[$(this).attr("id")]);
		}
		////alert($(this).attr("id")+"::::::"+$(this).val());	
	});
}

function cloneDataAndShowOverlay(overlayContainerId, overlayContentId){
	$("#trsprtOvrlId").html($("#"+overlayContentId).clone(true,true));
	cloneData(overlayContainerId, "trsprtOvrlId");
	$("#trsprtOvrlId").show();
	$("#"+overlayContainerId).html("");
	window.scroll(0, 0);
	document.documentElement.style.overflow = "hidden";
}

function cloneDataAndHideOverlay(overlayContainerId, overlayContentId){
	$("#"+overlayContainerId).html($("#"+overlayContentId).clone(true,true));
	cloneData("trsprtOvrlId", overlayContainerId);
	hideOvrl();
	$("#trsprtOvrlId").html("");
}

function populateDependentDropdownMInp(url, dependentDDownId,inpData) {
	
	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : inpData.data,
		success : function(data, textStatus) {
			// var stringifiedOptionsJSONArr = JSON.stringify(myJsonObjArr);
			populateDropdown(dependentDDownId, data);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
}


function populateDependentDropdown(sourceDDownId, url, dependentDDownId,idVal) {
	
	var sourceVal = $("#" + sourceDDownId).val();

	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : {
			objectId : sourceVal
		},
		success : function(data, textStatus) {
			var myJsonObj = JSON.parse(data);
			var myJsonObjArr = myJsonObj.objectList;
			// var stringifiedOptionsJSONArr = JSON.stringify(myJsonObjArr);
			populateDropdown(dependentDDownId, myJsonObjArr);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
}



function populateDropdown(dependentDDownId, optionsJSONArr) {
	// var optionsJSONArr = JSON.parse(stringifiedOptionsJSONArr);
	var isMultiple = false;
	if($("#" + dependentDDownId).attr("multiple")){
		isMultiple = true;
	}
	var htmlOptionsStr = getHTMLOptionsStringfromJSONArray(optionsJSONArr, isMultiple);
	$("#" + dependentDDownId).html(htmlOptionsStr);

}

function getHTMLOptionsStringfromJSONArray(optionsJSONArray, isMultiple) {
	var htmlOptionsStr = "";
	if(!isMultiple){
		htmlOptionsStr+="<option value='-1'>Select</option>";
	}
	for ( var optionsArrCounter = 0; optionsArrCounter < optionsJSONArray.length; optionsArrCounter++) {
		var optionJSON = optionsJSONArray[optionsArrCounter];
		htmlOptionsStr += "<option value='" + optionJSON.code + "'>"
				+ optionJSON.name + "</option>";
	}
	return htmlOptionsStr;
}

/*******************************************************************************************************/
/**
 * These methods are used to populate name-name pair instead of code-name pair in dependent dropdowns
 * Used: populating Resource Manager Name based on practice in Resource Workbench 
 */
function populateNameNameDependentDropdown(sourceDDownId, url, dependentDDownId,idVal) {
	
	var sourceVal = $("#" + sourceDDownId).val();

	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : {
			objectId : sourceVal
		},
		success : function(data, textStatus) {
			var myJsonObj = JSON.parse(data);
			var myJsonObjArr = myJsonObj.objectList;
			// var stringifiedOptionsJSONArr = JSON.stringify(myJsonObjArr);
			populateNameNameDropdown(dependentDDownId, myJsonObjArr);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
}


function populateNameNameDropdown(dependentDDownId, optionsJSONArr) {
	// var optionsJSONArr = JSON.parse(stringifiedOptionsJSONArr);
	var isMultiple = false;
	if($("#" + dependentDDownId).attr("multiple")){
		isMultiple = true;
	}
	var htmlOptionsStr = getHTMLOptionsStringNameNamefromJSONArray(optionsJSONArr, isMultiple);
	$("#" + dependentDDownId).html(htmlOptionsStr);

}

function getHTMLOptionsStringNameNamefromJSONArray(optionsJSONArray, isMultiple) {
	var htmlOptionsStr = "";
	if(!isMultiple){
		htmlOptionsStr+="<option value='-1'>Select</option>";
	}
	for ( var optionsArrCounter = 0; optionsArrCounter < optionsJSONArray.length; optionsArrCounter++) {
		var optionJSON = optionsJSONArray[optionsArrCounter];
		htmlOptionsStr += "<option value='" + optionJSON.name + "'>"
				+ optionJSON.name + "</option>";
	}
	return htmlOptionsStr;
}

/*******************************************************************************************************/


function populateDependentText(sourceDDownId, url, dependentTextBoxId) {
	if($("#"+sourceDDownId).val() !=-1){
		$.ajax({
			type : 'POST',
			url : url,
			timeout : 5000,
			async : false,
			data : {
				objectId : $("#"+sourceDDownId).val()
			},
			success : function(data, textStatus) {
			
				var myJsonObj = JSON.parse(data);
				var myJsonObjVal = myJsonObj.objectText;
				var str= myJsonObjVal[0].code;
				$("#"+dependentTextBoxId).val(str);
			},
			error : function(xhr, textStatus, errorThrown) {
				alert('request failed :' + errorThrown + " " + textStatus + " "
						+ xhr.toString());
			}
		});
	}else{
		$("#"+dependentTextBoxId).val('');
	}
}

function populateDependentTextBox(sourceDDownId, url, dependentTextBoxId) {
	if($("#"+sourceDDownId).val() !=-1){
		$.ajax({
			type : 'POST',
			url : url,
			timeout : 5000,
			async : false,
			data : {
				objectId : $("#"+sourceDDownId).val()
			},
			success : function(data, textStatus) {
				
				var myJsonObj = JSON.parse(data);
				var myJsonObjVal = myJsonObj.objectText;
				$("#"+dependentTextBoxId).val(myJsonObjVal);
			},
			error : function(xhr, textStatus, errorThrown) {
				alert('request failed :' + errorThrown + " " + textStatus + " "
						+ xhr.toString());
			}
		});
	}
}

function populateDependentTextField(sourceDDownId, url, dependentTextBoxId,combinedSrc) {
	
	if($("#"+sourceDDownId).val() !=-1 && $("#"+combinedSrc).val() !=-1){
	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : {
			objectId : $("#"+sourceDDownId).val()
		},
		success : function(data, textStatus) {
			
			var myJsonObj = JSON.parse(data);
			var myJsonObjVal = myJsonObj.objectText;
			var str;
			str= myJsonObjVal[0].code +"_"+document.getElementById(combinedSrc).options[document.getElementById(combinedSrc).selectedIndex].text;
			$("#"+dependentTextBoxId).val(str);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
  }
}
function combineDropDownAndPopulateDependentTextField(sourceDDownId, firstDropDownUrl, dependentTextBoxId,combinedSrc,secondDropDownUrl) {
	
	var str;
	if($("#"+sourceDDownId).val() !=-1 && $("#"+combinedSrc).val() !=-1){
	$.ajax({
		type : 'POST',
		url : firstDropDownUrl,
		async : false,
		timeout : 5000,
		data : {
			objectId : $("#"+sourceDDownId).val()
		},
		success : function(data, textStatus) {
			
			var myJsonObj = JSON.parse(data);
			var myJsonObjVal = myJsonObj.objectText;
			
			str= myJsonObjVal[0].code +"_";
			$("#"+dependentTextBoxId).val(str);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
	
	$.ajax({
		type : 'POST',
		url : secondDropDownUrl,
		async : false,
		timeout : 5000,
		data : {
			objectId : $("#"+combinedSrc).val()
		},
		success : function(data, textStatus) {
			var myJsonObj = JSON.parse(data);
			var myJsonObjVal = myJsonObj.objectText;
			str += myJsonObjVal[0].code;
			$("#"+dependentTextBoxId).val(str);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});	
  }
}

function logoutUser(){
	window.location.href=$("#contextPath").val()+"?logout=1";
}

function timeoutUser(){
	//alert("submitting timeoutForm");
	$("#timeoutForm").submit();
}


function fetchVersionDetails() {
	//alert($("#versionDetailsUrl").val());
	$.ajax({
		type : 'GET',
		url : $("#versionDetailsUrl").val(),
		async : false,
		timeout : 5000,
		success : function(htmlData, textStatus) {
			//alert("htmlData:::\n"+htmlData);
			showVersionOvrl(htmlData);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
}

function showVersionOvrl(htmlData){
	$("#trsprtOvrlId").html(htmlData);
	window.scroll(0,0);
	$("#trsprtOvrlId").show();
	document.documentElement.style.overflow = "hidden";
}

function hideVersionOvrl(){
	$("#trsprtOvrlId").hide();
	$("#trsprtOvrlId").html("");
	document.documentElement.style.overflow = "auto";
	document.body.style.marginRight='0px';
}

/*function populateTextBox(dependentDDownId, optionsJSONArr) {
	// var optionsJSONArr = JSON.parse(stringifiedOptionsJSONArr);
	var htmlOptionsStr = getHTMLStringfromJSONArray(optionsJSONArr);
	$("#" + dependentDDownId).html(htmlOptionsStr);

}

function getHTMLStringfromJSONArray(optionsJSONArray) {
	var htmlOptionsStr = "<option value='-1'>--select--</option>";
	for ( var optionsArrCounter = 0; optionsArrCounter < optionsJSONArray.length; optionsArrCounter++) {
		var optionJSON = optionsJSONArray[optionsArrCounter];
		htmlOptionsStr += "<option value='" + optionJSON.code + "'>"
				+ optionJSON.name + "</option>";
	}
	return htmlOptionsStr;
}*/

function hideAutoSuggest() {
	if (bodyJSON["ISCLICKEDTHROUGHEVENT"] != 'Y') {
		$(".autosuggestWrpr").hide();
	}
	bodyJSON["ISCLICKEDTHROUGHEVENT"] = 'N';
}

function showAutosugstDiv(id, url,textBoxId,dependDrpDwnId) {
	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : {
			textName : $("#"+textBoxId).val()
		},
		success : function(data, textStatus) {
			var myJsonObj = JSON.parse(data);
			var myJsonObjArr = myJsonObj.objectList;
			var htmlOptionsStr = generateHtmlAutosugst(myJsonObjArr,textBoxId,dependDrpDwnId,id);
			$("#" + id).html(htmlOptionsStr);
			$("#" + id).parent().show();
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});

}

function handleAutoSugstClick() {
	bodyJSON["ISCLICKEDTHROUGHEVENT"] = "Y";
}

function openPrjctNameSrch(value) {
	$("#rsrcMpngBigTextBoxId").val(value);
}
function generateHtmlAutosugst(optionsJSONArray,textBoxId,dependDrpDwnId,autosugstId) {
	var htmlOptionsStr = "<ul>";
	for ( var optionsArrCounter = 0; optionsArrCounter < optionsJSONArray.length; optionsArrCounter++) {
		var optionJSON = optionsJSONArray[optionsArrCounter];
		htmlOptionsStr += "<li onclick=\"showAutoSugstReslts('"+ optionJSON.code + "','"+textBoxId+"','"+dependDrpDwnId+"','"+optionJSON.name+"','"+autosugstId+"')\" id='" + optionJSON.code + "'>"
				+ optionJSON.name + "</li>";
	}
	htmlOptionsStr += "</ul>";
	return htmlOptionsStr;

}
function showAutoSugstReslts(id,textBoxId,dependDrpDwnId,textvalue,autosugstId){
	$("#"+textBoxId).val(textvalue);
	$("#"+dependDrpDwnId).val(id);
	$("#"+autosugstId).parent().hide();
	
}

function openNewWindow(url, widthInPercentage, heightInPercentage, windowTtl)
{
	var alwaysLowered 	= "no";
	var alwaysRaised 	= "yes";
	var dependent 		= "yes";
	var hotkeys 		= "no";
	var inrHeight		= (get_viewport_height()*heightInPercentage)/100; 	//90% of the viewport height
	var inrWidth		= (get_viewport_width()*widthInPercentage)/100; 	//90% of the viewport width
	var location		= "no";
	var menubar			= "no";
	var outerHeight		= inrHeight+10;
	var outerWidth		= inrWidth+10;
	var resizable 		= "yes";
	var left			= (screen.width  - outerWidth)/2;
	var top 			= (screen.height- get_viewport_height()-35);
	if(browserName == "chrome" || browserName == "safari" || browserName == "mozilla" || browserName == "firefox"){
		 top 			= (screen.height- get_viewport_height()-20);
	}
	var scrollbars		= "yes";
	var status			= "no";
	var titlebar		= "no";
	var toolbar			= "yes";
	var width			= outerWidth;
	var zlock			= "no";
	//create the parameter string
	var params 			= "";
	params 				+= "alwaysLowered="+alwaysLowered+", ";
	params 				+= "alwaysRaised="+alwaysRaised+", ";
	params 				+= "dependent="+dependent+", ";
	params 				+= "hotkeys="+hotkeys+", ";	
	if(browserName == "chrome" || browserName == "safari" || browserName == "mozilla" || browserName == "firefox"){
		params 				+= "inrHeight="+inrHeight+", ";
		params 				+= "inrWidth="+inrWidth+", ";
	}
	else{
		params 				+= "height="+inrHeight+", ";
		params 				+= "width="+inrWidth+", ";
	}
	params 				+= "location="+location+", ";		
	params 				+= "menubar="+menubar+", ";	
	params 				+= "outerHeight="+outerHeight+", ";	
	params 				+= "outerWidth="+outerWidth+", ";	
	params 				+= "resizable="+resizable+", ";	
	params 				+= "left="+left+", ";	
	params 				+= "top="+top+", ";	
	params 				+= "scrollbars="+scrollbars+", ";
	params 				+= "status="+status+", ";
	params 				+= "titlebar="+titlebar+", ";
	params 				+= "toolbar="+toolbar+", ";
	params 				+= "width="+width+", ";	
	params 				+= "z-lock="+zlock+", ";	
	
	//alert("url ::"+url);
	//create the window object
	var newwin 	= window.open(url, '', params);
	try{
		newwin.document.title = windowTtl;
		}catch(e){}
	if (window.focus){
		newwin.focus();
	}
	return false;
}

function showHelpContent(){
	openNewWindow($("#helpDetailsUrl").val(), 100, 100, 'EriPro Help');
	hideStaticOvrl('trsprtHelpWrprId');
}

function showHelpContentFinance(){
	openNewWindow($("#helpFinanceUrl").val(), 100, 100, 'EriPro Finance Help');
	hideStaticOvrl('trsprtHelpWrprId');
}
function showHelpContentFinanceFAQ(){
	openNewWindow($("#helpFinanceFaqUrl").val(), 100, 100, 'EriPro Finance FAQ');
	hideStaticOvrl('trsprtHelpWrprId');
}
function showHelpContentProgAccount(){
	openNewWindow($("#helpProgAccountUrl").val(), 100, 100, 'EriPro Program and Account Guide');
	hideStaticOvrl('trsprtHelpWrprId');
}

function initCalendar() {
	$("#updatedDate,#billDate,#orderDate,#partFromDate,#partToDate").datepicker({
		showOn : "button",
		buttonImage : "../includes/images/cal.gif",
		buttonImageOnly : true,
		dateFormat : "yy-mm-dd"
	});
		
}

function initGenericCalendar(id) {
	$("#"+id).datepicker({
		showOn : "button",
		buttonImage : "../includes/images/cal.gif",
		buttonImageOnly : true,
		dateFormat : "yy-mm-dd"/*,
		beforeShow : function(input) {
			alert("before sending");
		},
		onSelect : function(dateText, inst) {
			if (inst.id == "crtPrjStartDate") {
				alert("hi");// $("#toDtAssigned").datepicker("option","minDate",dateText);
			}

		}*/
	});
}

function markAllExistingRecords(totalDetailsID, checkAllID, rowCheckID){
			
			
			//alert("TOTAL RECORDS:"+ totalDetailsID);
			
			var existingIssuesIndex = parseInt($("#"+totalDetailsID).val(),10);

			for(var k=0;k<existingIssuesIndex;k++){
				if($("#"+checkAllID).attr("checked")){
					$("#"+rowCheckID+"_"+k).attr("checked","checked");
				}else{
					$("#"+rowCheckID+"_" +k).removeAttr("checked");
				}
		}
}

function setAllAsDefaultOption(dDownId) {
	//alert("in setAllAsDefaultOption");
		//alert("removing first option");
	//document.getElementById(dDownId).remove(0);
	var opt = document.createElement("option"); 
	opt.value = "0";
	opt.text = "All";
	document.getElementById(dDownId).options.add(opt,0);
	document.getElementById(dDownId).selectedIndex=0;	
}


function showAutoSugstReslts(id,textBoxId,dependDrpDwnId,textvalue,autosugstId){
	$("#"+textBoxId).val(textvalue);
	$("#"+dependDrpDwnId).val(id);
	if($("#"+dependDrpDwnId).get(0).tagName.toUpperCase()=="SELECT"){
		$("#"+dependDrpDwnId).change();
	}
	$("#"+autosugstId).parent().hide();
	
}

function parseDate(dateStr){
	var dateStrArr = dateStr.split("-");
	var YYYY = dateStrArr[0];
	var MM = dateStrArr[1];
	var DD = dateStrArr[2];
	var retDate = new Date(parseInt(YYYY, 10), parseInt(MM, 10) - 1, parseInt(DD, 10)); 
	return retDate;
}

function getDateDiff(dateId1, dateId2){
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date();
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date();
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	if(dateDiff>0){
		retval = true;
	}else{
		retval = false;
	}
	//alert("getDateDiff :"+retval);
	return retval;
}

function beforeDate(dateId1, dateId2){
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date();
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date();
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	
	if(dateDiff<=0){
		retval = true;
	}else{
		retval = false;
	}
	return retval;
}


function afterDate(dateId1, dateId2){
	
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date();
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date();
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	
	if(dateDiff>=0){
		retval = true;
	}else{
		retval = false;
	}
	//alert("afterDate :"+retval+"::"+date1.getTime()+"::::"+new Date(parseInt(2013, 10), parseInt(2, 10) - 1, parseInt(25, 10)).getTime() +"::"+new Date().getTime());
	return retval;
	
}

function afterDateYYYYMMDD(dateId1, dateId2){
	
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date((new Date()).getFullYear(), (new Date()).getMonth(), (new Date()).getDate());
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date((new Date()).getFullYear(), (new Date()).getMonth(), (new Date()).getDate());
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	
	if(dateDiff>=0){
		retval = true;
	}else{
		retval = false;
	}
	//alert("afterDateYYYYMMDD :"+retval+"::"+date1.getTime()+"::"+date2.getTime()+"::::"+new Date(parseInt(2013, 10), parseInt(2, 10) - 1, parseInt(25, 10)).getTime() +"::"+new Date().getTime());
	return retval;
	
}


function checkStartDateWithCurrDate(dateId){
	   
	   if(dateId.length>0){
		   var newDate = $.datepicker.formatDate('yy-mm-dd', new Date());
		   var startDate = $.datepicker.formatDate('yy-mm-dd', parseDate($("#"+dateId).val()));
		   
		   if(startDate < newDate){
				return false;
			}else{
				return true;
			};
		}else{
			alert("Start Date is null");
		}
	}

function getMetricsDateDiff(dateId1, dateId2){
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date();
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date();
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	if(dateDiff>=0){
		retval = true;
	}else{
		retval = false;
	}
	return retval;
}


function checkEndDate(dateId){
   
   if(dateId.length>0){
	   var newDate = $.datepicker.formatDate('yy-mm-dd', new Date());
	   var endDate = $.datepicker.formatDate('yy-mm-dd', parseDate($("#"+dateId).val()));
	   
	   if(endDate < newDate){
			return false;
		}else{
			return true;
		};
	}else{
		alert("ExpectedEndDateID is null");
	}
}


function checkStartDate(dateId){
	   
	   if(dateId.length>0){
		   var newDate = $.datepicker.formatDate('yy-mm-dd', new Date());
		   var endDate = $.datepicker.formatDate('yy-mm-dd', parseDate($("#"+dateId).val()));
		   
		   if(endDate > newDate){
				return false;
			}else{
				return true;
			};
		}else{
			alert("ExpectedEndDateID is null");
		}
	}
function resourceGetDateDiff(dateId1, dateId2){
	
	var date1;
	var date2;
	var retval;
	
	if(dateId1 && dateId1.length>0){
		date1 = parseDate($("#"+dateId1).val());
	}else{
		date1 = new Date();
	}
	
	if(dateId2 && dateId2.length>0){
		date2 = parseDate($("#"+dateId2).val());
	}else{
		date2 = new Date();
	}
	
	var dateDiff = date1.getTime()-date2.getTime();
	if(dateDiff>=0){
		retval = true;
	}else{
		retval = false;
	}
	return retval;
}

function chkTextContent(id) {
	var retval = true;
	var strArry = $("#"+id).val().split("");
	//alert("strArray:"+ strArry);
	var arry = new Array();
	for ( var i in strArry) {
		arry[i] = strArry[i].charCodeAt(0);
		if ((arry[i] != 10) && (arry[i] != 13) && !((arry[i] >= 32) && (arry[i] < 126)) || (arry[i] == 35)|| (arry[i] == 60) || (arry[i] == 62)) {
				retval = false;
				break;
		}
		
	}
	return retval;
}

function chkAdditionalTextContent(id) {
	var retval = true;
	var strArry = $("#"+id).val().split("");
	var arry = new Array();
	for ( var i in strArry) {
		arry[i] = strArry[i].charCodeAt(0);
		if (!((arry[i] >= 32) && (arry[i] < 126)) 
				|| (arry[i] == 60) || (arry[i] == 62) || (arry[i] == 64) || (arry[i] == 33) 
				|| (arry[i] == 35) || (arry[i] == 36) || (arry[i] == 37) || (arry[i] == 38) 
				|| (arry[i] == 39) || (arry[i] == 42) || (arry[i] == 94))
		{
			retval = false;
			break;
		}
	}
	return retval;
}

function chkCommaInCurrency(id) {
	var retval = false;
	var strArry = $("#"+id).val().split("");
	//alert("strArray:"+ strArry);
	var arry = new Array();
	for ( var i in strArry) {
		arry[i] = strArry[i].charCodeAt(0);
		if (arry[i] != 44) {
				retval = true;
				break;
		}
		
	}
	return retval;
}
function replaceCommaInCurrency(id){
	var value = $("#"+id).val();
	//alert("old--"+value);
	var newValue = value.replace(new RegExp(",","g"),"");
	return newValue;
	}

function validateDate(DateStr) {
	  var bits = $("#"+DateStr).val().split('-');
	  var d = bits[2], m  = bits[1], y = bits[0];
	  // Assume not leap year by default (note zero index for Jan)
	  var daysInMonth = [31,28,31,30,31,30,31,31,30,31,30,31];

	  // If evenly divisible by 4 and not evenly divisible by 100,
	  // or is evenly divisible by 400, then a leap year
	  if ( (!(y % 4) && y % 100) || !(y % 400)) {
	    daysInMonth[1] = 29;
	  }
	  return d <= daysInMonth[--m];
	}

/*
 * This function populates the title for the options in a select box.
 */
function HighlightListTooltip() {
	$("select").each(function() {
	var i = 0;
	var s = this;
	for (i = 0; i < s.length; i++)
	s.options[i].title = s.options[i].text;
	if (s.selectedIndex > -1){
			s.onmousemove = function() {
					s.title = s.options[s.selectedIndex].text;
			};
	}});
	
	$("input:text").each(
	function(){		
	var t= this;
	t.onmousemove = function(){		
		t.title = t.value;
	};
	});
}

/*
 * This function populates the title for the options in a select box.
 */
function disableTag(id) {
	$("#"+id).attr('disabled', true);
}

function showTitle(id){
	$("#"+id).attr('disabled', false);
	if($("#"+id).val() != -1){
		$("#"+id).attr('title', $("#"+ id +" option:selected").text());
	}else{
		$("#"+id).attr('title', 'Select');
	}
}

function showTitleForTextBox(id){
	$("#"+id).attr('disabled', false);
	if($("#"+id).val() != ""){
		$("#"+id).attr('title', $("#"+ id).val());
	}else{
		$("#"+id).attr('title', "");
	}
}
/**
 * This function can be used to show any hidden div.
 * @param id
 */
function showDiv(id){
	if($("#programID").val() != -1){
		$("#"+id).show();
	}else{
		$("#"+id).hide();
	}
}

/*	Added by soumi for adding greater than zero validation 
	in metrics
*/
function validateGreaterThanZero(id) {
	  var val = $("#"+id).val();

	  // If less than zero, then reject
	  if ((parseFloat(val)) <= 0) {
	    return false;
	  }
	  return true;
}

/**
 * Returns False in case neither of the text box is selected, 
 * or both the text boxes are selected.
 * @param textBox1
 * @param txtBox2
 * @returns flag
 */
function eitherOrSelectionOfTwoTextBox(textBox1, textBox2){
	return $("#"+textBox1).val()==-1?($("#"+textBox2).val()==-1?false:true):($("#"+textBox2).val()==-1?true:false);
}
/**
 * Returns False in case all the text boxes are selected.
 * @param textBox1
 * @param txtBox2
 * @param txtBox3
 * @returns flag
 */

function eitherOrSelectionOfThreeTextBoxes(textBox1, textBox2, textBox3){
	var textBox1Val = $("#"+textBox1).val();
	var textBox2Val = $("#"+textBox2).val();
	var textBox3Val = $("#"+textBox3).val();
	if(textBox1Val==-1 && textBox2Val==-1 && textBox3Val==""){
		return false;
	}else
		return true;
	
}

function selectionOfBothTextBoxes(textBox1, textBox2){
	var textBox1Val = $("#"+textBox1).val();
	var textBox2Val = $("#"+textBox2).val();
	
	if(textBox1Val == "" && textBox2Val != -1){
		return false;
	}else if(textBox1Val != "" && textBox2Val == -1){
		return false;
	}else
		return true;
}
/*
 * checks if duplicate network ids exist or not
 */
function chkDuplicateNetwrkId(){
	var netList = new Array();
	
	$("input[id^=networkIdList]").each(function() {
		if(($(this).val()).length == 0){
			$(this).val(0);
		}
		if($(this).val() != 0){
		netList.push($(this).val());
		}
		// alert($(this).attr("id")+"::::::"+$(this).val());
	});
	var sorted_arr = netList.sort();
	for(var i=0 ; i<sorted_arr.length; i++)
	{
		//alert(sorted_arr[i]);
		if (sorted_arr[i + 1] == sorted_arr[i]) {
	        return false;
	    }

	}
	return true;
}

function showAsOverlay(overlayContainerId){
	
	$("#trsprtOvrlId").show();
	$("#"+overlayContainerId).show();
	window.scroll(0, 0);
	document.documentElement.style.overflow = "hidden";
}

function hideAsOverlay(overlayContainerId){

	$("#trsprtOvrlId").hide();
	$("#"+overlayContainerId).hide();
	window.scroll(0, 0);
	document.documentElement.style.overflow = "auto";
	document.body.style.marginRight='0px';
}

/**
 * Returns False in case all the text boxes are selected.
 * @param textBox1
 * @param txtBox2
 * @param txtBox3
 * @returns flag
 */

function selectionOfMoreThanOneTextBoxes(textBox1, textBox2, textBox3){
	var textBox1Val = $("#"+textBox1).val();
	var textBox2Val = $("#"+textBox2).val();
	var textBox3Val = $("#"+textBox3).val();
	if(textBox1Val != -1 && textBox2Val != -1 && textBox3Val != ""){
		return false;
	}else if(textBox1Val == -1 && textBox2Val != -1 && textBox3Val != ""){
		return false;
	}else if(textBox1Val != -1 && textBox2Val == -1 && textBox3Val != ""){
		return false;
	}else if(textBox1Val != -1 && textBox2Val != -1 && textBox3Val == ""){
		return false;
	}
	else
		return true;
	
}

function showDiv(id){
	$("#"+id).show();
}

function intRegExMethod(id){
	var intRegex = /^\d+$/;
	var index = id.substr(id.length - 1);
	return intRegex.test(index);
}


/**
 * This method generates a validation error with the error message,
 * and the text box id for which the error needs to be generated.
 * @param textBoxId -- The id for which error is generated.
 * @param errorMsg -- The error message to be displayed.
 * @param id -- The div id on which the error would be displayed.
 */
function createErrorMessageOnDiv(textBoxId, errorMsg, id){
	var budgetIdCheckFldVldJSON = {
		requiredValidations : [],
	    formatValidations : [],
		dataTypeValidations : [
		                       {
		                    	   id : textBoxId, 
		                    	   msg : errorMsg,
		                    	   checker : function(){
		                    		   return false;
		                    	   }
		                       }
		                       ]
	};
	validator.initiate(budgetIdCheckFldVldJSON);
	var options = {
		errorMsgDiv : id
	};
	validator.validatewithoutscroll(options);
	if (!validator.failedElemsExist) {
		$('#'+id).hide();
	}else{
		$("html").scrollTop(0);
	}
}

/**
 * Here in this method the field value is the validation message in
 * case the validation fails. The fields for which the validation fails
 * the value could be anything except 'Y' or 'NA'.
 * @param id
 * @param checkerVal
 */
function checkFieldForValidation(id, checkerVal){
	//Checking if the id ends with an Integer.
	if(intRegExMethod(id)){
		validator.addDataTypeValidationEntry({
			id : id, 
			msg : checkerVal,
			checker : function(){
					/*
						Checks if the Value entered is Y or NA,
						in case its not the error message is displayed,
						else the validation is passed.
					*/
	   				return (checkerVal != "Y" && checkerVal != "NA")?false:true;
			}	
		});
	}
}

/**
 * Validate the greater date on Event fire.
 * @param startDate
 * @param endDate
 * @returns {Boolean}
 */
function validateGreaterDate(startDate, endDate){
	
	var startDateVal = $('#'+startDate).val();
	var endDateVal = $('#'+endDate).val();
	
	//alert(startDate+"//START DATE VALUE:" + startDateVal + " - "+ endDate + "//END DATE VALUE:" + endDateVal);
	
	var startDateTitle = $('#'+startDate).attr("validatorlabel");
	var endDateTitle = $('#'+endDate).attr("validatorlabel");
	
	var budgetTblFldVldJSON = {
			requiredValidations : [ ],		//{id: fldId}
		    formatValidations : [{id : startDate, format: "date"},
		                         {id : endDate, format: "date"}],
			dataTypeValidations : [
			                       {
			                    	   id : startDate, 
			                    	   msg : endDateTitle + " must be greater than "+ startDateTitle,
			                    	   checker : function(){
			                    		   return getDateDiff(endDate, startDate);
			                    	   }
			                       }
			                       ]
	};
	validator.initiate(budgetTblFldVldJSON);
	var options = {
			errorMsgDiv : "tblsMsgCntnrId"
		};
		validator.validatewithoutscroll(options);
		if (!validator.failedElemsExist) {
			$('#tblsMsgCntnrId').hide();
			return true;
		}
		else{
			return false;
		}
}