function openItemAutosuggest(itemAutosuggestCntnr) {
	$("#ordrRptItmSearchLbl").show();
	$("#ordrRptItmSearchTxtCntnr").show();
	
	$("#"+itemAutosuggestCntnr).show();
}

function openClientAutosuggest(ordrRptClntAutoSuggestCntnr, itemAutosuggestCntnr){
	$("#"+itemAutosuggestCntnr).show();
	$("#"+ordrRptClntAutoSuggestCntnr).show();
}

function showAutosugstItems(id, url,textBoxId,dependDrpDwnId, categoryId) {
	var jsonToSend ={};
	jsonToSend.textName = $("#"+textBoxId).val();
	if($("#"+categoryId).val() && $("#"+categoryId).val().length>0){
		jsonToSend.category = $("#"+categoryId).val();
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

function showAutosugstClients(id, url,textBoxId,dependDrpDwnId) {
	var jsonToSend ={};
	jsonToSend.textName = $("#"+textBoxId).val();
	
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

function populateItemsForCategory(categoryId, itemId){
	
	var inpData = {
		data : {
			textName : "",
			category : $("#"+categoryId).val()
		}
	};
	
	populateDependentDropdownMInp($("#contextPath").val()+"/items/fetchItemsAutoSuggest.htm", itemId, inpData);
}


function addNewItemRow() {
	
	var allAddedItemsIndex = parseInt($("#allAddedItems").val(), 10);
	allAddedItemsIndex++;
	var addedItemTmpltHTML = $("#addedItemTmpltCntnr").html();
	var addedItemHTML = addedItemTmpltHTML.replace(new RegExp("SECTIONINDEX", "g"), allAddedItemsIndex);
	$("#addedItemsCntnr").append(addedItemHTML);
	$("#inrPgBdCntntBorderWrpr_"+ allAddedItemsIndex).show();
	$("#allAddedItems").val(allAddedItemsIndex);
	
}

function deleteItemRow(deletedItemIndex) {
	$("#isDeleted_" + deletedItemIndex).val("Y");
	$("#inrPgBdCntntBorderWrpr_"+ deletedItemIndex).hide();
}

function generateItemAvailabilityJSON(){
	var itemAvailabilityJSON = {};
	itemAvailabilityJSON.requiredValidations = [];
	itemAvailabilityJSON.formatValidations = [];
	itemAvailabilityJSON.dataTypeValidations = [];
	
	for(var k=0;k<=parseInt($("#allAddedItems").val(), 10);k++){
		if($("#isDeleted_" + k).val()!="Y"){
			itemAvailabilityJSON.requiredValidations.push({id : "itemId_"+k});
			itemAvailabilityJSON.requiredValidations.push({id : "itmQty_"+k});
			
			itemAvailabilityJSON.formatValidations.push({id : "itmQty_"+k, format: "number"});
		}
		
	}
	
	return itemAvailabilityJSON;
}

function checkAvailabilityForItems(){
	
	validator.initiate(generateItemAvailabilityJSON());
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		$("#addedItemTmpltCntnr").html("");
		var onSuccessCallBack = {
			success : showAvailabilityStats
		};
		submitPgBdForm("manageItemsForm", $("#contextPath").val()+"/items/checkAvailabilityForItems.htm", onSuccessCallBack);
	}
	
}

function showAvailabilityStats() {
	$("#partsAvailabilityStatsWrpr").show();
	showPgBdCntnr();
}


function placeOrderForItems(){
	var itemAvailabilityJSON = generateItemAvailabilityJSON(); 
	itemAvailabilityJSON.requiredValidations.push({id : "clientName"});
	itemAvailabilityJSON.requiredValidations.push({id : "orderDate"});
	itemAvailabilityJSON.formatValidations.push({id : "orderDate", format: "date"});
	
	validator.initiate(itemAvailabilityJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		$("#addedItemTmpltCntnr").html("");
		var onSuccessCallBack = {
			success : showAvailabilityStats
		};
		submitPgBdForm("manageItemsForm", $("#contextPath").val()+"/items/placeOrderForItems.htm", onSuccessCallBack);
	}
}

function getOrderSearchResults(){

	var onSuccessCallBack = {
		success : showOrderSearchResults
	};
	submitPgBdForm("orderReportForm", $("#contextPath").val()+"/items/getOrderSearchResults.htm", onSuccessCallBack);

}

function showOrderSearchResults() {
	
    $("#orderSearchResultsTbl").dataTable({
        "sPaginationType": "full_numbers",
        "bJQueryUI": true,
        "bFilter": false,
        "bRetrieve": true,
        "aoColumns": [
		              null,
		              { "sType": dataTableSortExtensions.ddmmyyyy_hyphen.identifier },
		              null,
		              null,
		              null,
		              { "sType": dataTableSortExtensions.ddmmyyyy_hyphen.identifier }
		             ]
    });

	showPgBdCntnr();
}

function getDetailsForOrderId(orderId){
	
	$("#orderId").val(orderId);

	var onSuccessCallBack = {
		success : showOrderDetails
	};
	submitPgBdForm("orderReportForm", $("#contextPath").val()+"/items/getDetailsForOrderId.htm", onSuccessCallBack);

}


function showOrderDetails() {
	
    $("#orderSearchResultsTbl").dataTable({
        "sPaginationType": "full_numbers",
        "bJQueryUI": true,
        "bFilter": false,
        "bRetrieve": true,
        "aoColumns": [
		              null,
		              { "sType": dataTableSortExtensions.ddmmyyyyhhmm_hyphen.identifier },
		              null,
		              null,
		              null
		             ]
    });

	showPgBdCntnr();
	
	$("#manageItemOrderBeanOrderDate").datepicker({
		showOn : "button",
		buttonImage : "../includes/images/cal.gif",
		buttonImageOnly : true,
		dateFormat : "yy-mm-dd"
	});
	
	showAsOverlay("trsprtEditOrderOvrlOuterId");
}


function addNewItemRowEditOrder() {
	
	var allAddedItemsIndex = parseInt($("#allAddedItems").val(), 10);
	allAddedItemsIndex++;
	var addedItemTmpltHTML = $("#addedItemTmpltCntnr").html();
	var addedItemHTML = addedItemTmpltHTML.replace(new RegExp("SECTIONINDEX", "g"), allAddedItemsIndex);
	addedItemHTML = addedItemHTML.replace(new RegExp("_addedItems_", "g"), "manageItemOrderBean.addedItems");
	$("#addedItemsCntnr").append(addedItemHTML);
	$("#inrPgBdCntntBorderWrpr_"+ allAddedItemsIndex).show();
	$("#allAddedItems").val(allAddedItemsIndex);
	
}

function deleteItemRowEditOrder(deletedItemIndex) {
	$("#isDeleted_" + deletedItemIndex).val("Y");
	$("#inrPgBdCntntBorderWrpr_"+ deletedItemIndex).hide();
}


function checkAvailabilityForItemsEditOrder(){
	validator.initiate(generateItemAvailabilityJSON());
	var options = {
		errorMsgDiv : "ovrlMsgCntnrEditOrder"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		$("#addedItemTmpltCntnr").html("");
		var onSuccessCallBack = {
			success : showAvailabilityStatsEditOrder
		};
		submitPgBdForm("orderReportForm", $("#contextPath").val()+"/items/checkAvailabilityForItemsEditOrder.htm", onSuccessCallBack);
	}
}

function showAvailabilityStatsEditOrder() {
	showOrderDetails();
	$("#partsAvailabilityStatsWrpr").show();
}

function updateOrderForItems(){
	var itemAvailabilityJSON = generateItemAvailabilityJSON(); 
	itemAvailabilityJSON.requiredValidations.push({id : "manageItemOrderBeanClientName"});
	itemAvailabilityJSON.requiredValidations.push({id : "manageItemOrderBeanOrderDate"});
	itemAvailabilityJSON.formatValidations.push({id : "manageItemOrderBeanOrderDate", format: "date"});
	validator.initiate(itemAvailabilityJSON);
	var options = {
		errorMsgDiv : "ovrlMsgCntnrEditOrder"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		hideAsOverlay('trsprtEditOrderOvrlOuterId');
		$("#addedItemTmpltCntnr").html("");
		var onSuccessCallBack = {
			success : showOrderSearchResults
		};
		submitPgBdForm("orderReportForm", $("#contextPath").val()+"/items/updateOrderForItemsEditOrder.htm", onSuccessCallBack);
	}
}

function viewBill(billId){
	
	var url =$("#contextPath").val()+"/parts/viewBillDetails.htm?billId="+billId;
	openNewWindow(url, 100, 100, 'View Bill Details');
}
