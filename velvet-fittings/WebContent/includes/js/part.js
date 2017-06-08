
function populateDependentpartsInfo(sourceDDownId, url) {
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

				$("#name").val(data.name);
				$("#code").val(data.code);
				$("#partsId").val(data.partsId);
				$("#quantity").val(data.quantity);
				$("#threshold").val(data.threshold);
				$("#categoryId").val(data.categoryId); 
				
				if(parseInt($("#threshold").val()) > parseInt($("#quantity").val())){
					$("#msgCntnrId").addClass("errorMsg");
					$("#msgCntnrId").html("Alert:Threshold has been breached.Please add the stock in the System.");
					$("#msgCntnrId").show();
				}else{
					$("#msgCntnrId").hide();
				}
				
			},
			error : function(xhr, textStatus, errorThrown) {
				alert('request failed :' + errorThrown + " " + textStatus + " "
						+ xhr.toString());
			}
		});
	}
}

function populateDependentDropdownForParts(sourceDDownId, url, dependentDDownId,idVal) {
	
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
			// var stringifiedOptionsJSONArr = JSON.stringify(myJsonObjArr);
			populateDropdown(dependentDDownId, data);
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
}

function showAddPartsSection(){
	$("#deletePartsSctn").hide();
	$("#addPartsSctn").show();
	$("#dateSection").show();
	$("#addBtnSection").show();
	$("#deleteBtnSection").hide();
}

function showDeletePartsSection(){
	$("#addPartsSctn").hide();
	$("#deletePartsSctn").show();
	$("#dateSection").show();
	$("#addBtnSection").hide();
	$("#deleteBtnSection").show();
}

function addPartsToInventory(){
	validator.initiate(addPartsValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		var onSuccessCallBack = {
			success : showAddDeletePartsForm
		};
		submitPgBdForm("addDeletePartsForm", $("#contextPath").val()+"/parts/submitAddDeleteParts.htm", onSuccessCallBack);
	}
}

function deletePartsToInventory(){
	validator.initiate(deletePartsValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		var onSuccessCallBack = {
			success : showAddDeletePartsForm
		};
		submitPgBdForm("addDeletePartsForm", $("#contextPath").val()+"/parts/submitAddDeleteParts.htm", onSuccessCallBack);
	}
}

function getPartSearchResults(){

		var onSuccessCallBack = {
			success : showPartsSearchResults
		};
		submitPgBdForm("addDeletePartsForm", $("#contextPath").val()+"/parts/getPartSearchRslts.htm", onSuccessCallBack);
	
}

function getPartThresholdResults(){

	var onSuccessCallBack = {
		success : showPartsThresholdResults
	};
	submitPgBdForm("addDeletePartsForm", $("#contextPath").val()+"/parts/getPartthresholdRslts.htm", onSuccessCallBack);

}

function showAddDeletePartsForm() {
	showPgBdCntnr();
}


function showPartsSearchResults() {
	
    $("#partSearchTable").dataTable({
        "sPaginationType": "full_numbers",
        "bJQueryUI": true,
        "bFilter": false,
        "bRetrieve": true
    });

	showPgBdCntnr();
}

function showPartsThresholdResults() {
	
    $("#partThresholdTable").dataTable({
        "sPaginationType": "full_numbers",
        "bJQueryUI": true,
        "bFilter": false,
        "bRetrieve": true
    });

	showPgBdCntnr();
}


/**
 * to display the auto search div.
 * @param id -- Div id.
 */
function opensearchBoxAccount(id) {
	if($("#" + id).is(":visible")){
		$("#" + id).hide();
	}else{
		$("#" + id).show();
	}
}

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
			var htmlOptionsStr = generateHtmlAutosugst(data,textBoxId,dependDrpDwnId,id);
			$("#" + id).html(htmlOptionsStr);
			$("#" + id).parent().show();
		},
		error : function(xhr, textStatus, errorThrown) {
			alert('request failed :' + errorThrown + " " + textStatus + " "
					+ xhr.toString());
		}
	});
	
}