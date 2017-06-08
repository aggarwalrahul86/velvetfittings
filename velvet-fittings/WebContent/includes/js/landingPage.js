
$(document).ready(
	function(){
		loadTabContent();
		landingPageOnloadHandler();
	}
);

function loadTabContent(){
	//alert("in loadTabContent()");
	initializeTabContentForm();
	var landingTab = $("#landingTab").val();
	$("#"+landingTab).click();
}

function tabClickHandler(clickedTabId, clickedTabUrl){
	$("#inrPageLeftMenuTextCntnrId").hide();
	$("#leftMenuThrbrCntnrId").show();
	$("#inrPgBdTextCntnrId").hide();
	$("#pgBdThrbrCntnrId").show();
	$(".lndnpgTab").removeClass("activeTab");
	$("#"+clickedTabId).addClass("activeTab");
	//alert("added activeTab class to :"+clickedTabId);
	$("#tabContentForm").attr("action", $("#contextPath").val()+clickedTabUrl);
	//alert("submitting tabContentForm :"+$("#tabContentForm").attr("action"));
	$("#tabContentForm").submit();
}

function initializeTabContentForm(){
	$('#tabContentForm').ajaxForm({
		target : '#lndnpgContentId',
		success : tabContentOnloadHandler,
		error: function(){
			//alert("Error");
		}
	});
}

function tabContentOnloadHandler(){
	//alert("successfuly loaded lndnpgContentId");
	if($("#SESSION_EXPIRY_IDENTIFIER").val()=="Y"){
		if($("#SSO_ENABLED_FLAG").val()=="Y"){
			window.location.href = $("#SESSION_EXPIRY_REDIRECT_URL").val();
		}else{
			window.location.href = $("#CONTEXT_FULL_PATH").val();
		}
	}else{
		initializeLeftMenuForm();
		var landingMenu = $("#landingMenu").val();
		$("#"+landingMenu).click();
	}
	
}

function initializeLeftMenuForm(){
	$('#leftmenuForm').ajaxForm({
		target : '#inrPgBdTextCntnrId',
		success : showPgBdCntnr,
		error: function(){
			//alert("Error");
		}
	});
}

function menuClickHandler(clickedMenuId, clickedMenuUrl){
	$("#inrPgBdTextCntnrId").hide();
	$("#pgBdThrbrCntnrId").show();
	$(".leftMenuLink").removeClass("activeMenu");
	$("#"+clickedMenuId).addClass("activeMenu");
	$("#leftmenuForm").attr("action", $("#contextPath").val()+clickedMenuUrl);
	
	//alert("submitting leftmenuForm :"+$("#leftmenuForm").attr("action"));
	$("#leftmenuForm").submit();
}