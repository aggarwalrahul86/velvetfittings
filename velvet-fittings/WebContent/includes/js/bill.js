function getOrderInfo(){
	validator.initiate(getOrderValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		var onSuccessCallBack = {
			success : showGenerateBillForm
		};
		submitPgBdForm("generateBillForm", $("#contextPath").val()+"/parts/getOrderInfo.htm", onSuccessCallBack);
	}
}

function generateBillDetails(){
	validator.initiate(generateBillValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		var onSuccessCallBack = {
			success : showViewBillForm
		};
		submitPgBdForm("generateBillForm", $("#contextPath").val()+"/parts/generateBillDetails.htm", onSuccessCallBack);
	}
}


function viewBillDetails(){
	
	var url =$("#contextPath").val()+"/parts/viewBillDetails.htm?billId="+$("#billId").val();
	openNewWindow(url, 100, 100, 'View Bill Details');
}

function printBill(){
	window.print();
}

function showGenerateBillForm() {
	showPgBdCntnr();
	$("#SubOrderSection").show();
	$("#generateButonSection").show();
	$("#viewBillButton").hide();
	calculateTotalBill();
}
function showViewBillForm() {
	showPgBdCntnr();
	$("#SubOrderSection").show();
	$("#generateButonSection").hide();
	$("#viewBillButton").show();
	calculateTotalBill();
	calculateNetAmount();
}

function calculateTotalBill(){
	var counter = parseInt($("#totalItemsCount").val());
	var totalCount = 0;
	for(var i=0;i <counter; i++ ){
		totalCount = totalCount+parseInt($("#itemsListTransactedPrice"+i).val());
	}
	$("#totalQuantityPrice").val(totalCount);
	$("#totalNetAmount").val(totalCount);
	
}

function calculateNetAmount(){
	var taxAmount = 0;
	var discAmount = 0;
	if(isNaN(parseFloat($("#totalTaxAmount").val()))){
		taxAmount = 0;
	}else{
		taxAmount = parseFloat($("#totalTaxAmount").val());
	}
	if(isNaN(parseFloat($("#totalDiscountAmount").val()))){
		discAmount = 0;
	}else{
		discAmount = 0.01 * parseFloat($("#totalDiscountAmount").val()) * parseFloat($("#totalQuantityPrice").val());
	}
	$("#discountAmount").val(parseFloat(discAmount).toFixed(2));	
	var netAmnt = parseFloat($("#totalQuantityPrice").val()) + taxAmount  - discAmount;

	$("#totalNetAmount").val(parseFloat(netAmnt).toFixed(2));	
}

function cancelOrderForItems(){
	validator.initiate(getOrderValidationJSON);
	var options = {
		errorMsgDiv : "msgCntnrId"
	};
	validator.validate(options);
	if (!validator.failedElemsExist) {
		var onSuccessCallBack = {
			success : loadGenerateBillForm
		};
		submitPgBdForm("generateBillForm", $("#contextPath").val()+"/parts/cancelOrderForItems.htm", onSuccessCallBack);
	}
}

function loadGenerateBillForm() {
	showPgBdCntnr();
}