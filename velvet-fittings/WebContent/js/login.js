/*$(document).ready(function() {
	homePageOnloadHandler();
	
	$('#confirmPasswordId').bind('cut copy paste', function(event) {
        event.preventDefault();
    });
	onloadLoginRole();
	
	$('input[id*="Password"]').bind('cut copy paste', function(event) {
        event.preventDefault();
    });
	1
});
*/
/*function onSubmit() {
	var targetUrl = "parts/submitParts";

	$.ajax({
		type : "POST",
		url : targetUrl,
		contentType : "application/json; charset=utf-8",
		dataType : "json",
		data : {
			addedQnty : $("#addedQnty").val(),
			deleteQnty : $("#deleteQnty").val()
		},
		success : function(data, status, xhr) {
			$("#msgCntnrId").html("Parts successFully registered and the name is"+data.partsId);				
			$("#msgCntnrId").show();
			alert("success");
		},
		error : function(request, status, error) {
			alert('request failed :' + errorThrown);
		}
	});
	*/
	function onSubmit(){

		$("#partsFrm").attr("action", "parts/submitParts");
		$("#partsFrm").ajaxForm({
			target : '#partsInventoryId',
			data : { },
			success : function(data, textStatus) {
				alert("Success");
			},		
			error : function() {
				alert("Error");
			}
		});
		//alert($("#partsFrm").attr("action"));
		$("#partsFrm").submit();
	}


	
