function showDependentAutosugstDiv(id, url,textBoxId,dependDrpDwnId,sourceId) {
	var jsonToSend ={};
	jsonToSend.textName = $("#"+textBoxId).val();
	if(sourceId && sourceId.length>0){
		jsonToSend.objValue = $("#"+sourceId).val();
	}
	$.ajax({
		type : 'POST',
		url : url,
		async : false,
		timeout : 5000,
		data : jsonToSend,
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
	if(dependDrpDwnId.length>0){
		$("#"+dependDrpDwnId).val(id);
	}
	$("#"+autosugstId).parent().hide();
	
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
