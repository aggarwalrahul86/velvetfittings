<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<%
		String contextPath = request.getContextPath();
	%>
	<head>
		<title>Add/Delete Parts</title>
		<jsp:include page="/jsps/commonMetaData.jsp"></jsp:include>
		<jsp:include page="/jsps/commonScript.jsp"></jsp:include>
	</head>
	
	<body class="body" id="body" onclick="hideAutoSuggest()">
		<jsp:include page="/jsps/header.jsp"></jsp:include>
		<!-- Body Start -->
		<div class="pageBodyGrdnt landingpgBdWrpr">
			<input type="hidden" name="contextPath" id="contextPath" value="<%=contextPath%>"/>
			<div class="landingpgBd">
				<div class="bdHPd"></div>
				<div class="landingpgBdInr">
					<!-- Tab Cntnr Start  -->
					<jsp:include page="tabContainer.jsp"></jsp:include>
					<!-- Tab Cntnr End -->
					<!-- BMD Page Specific Body Part Start -->
					<div class="lndnpgContentCntnr">
						<form method="get" name="tabContentForm" id="tabContentForm" action="" style="display:none"></form>
						<div class="lndnpgCntntWrpr" id="lndnpgCntntWrprId">
							<div class="lndnpgContent" id="lndnpgContentId">
								<!-- TbCntnt Start -->
								<jsp:include page="/jsps/templateTabContent.jsp" ></jsp:include>
								<!-- TbCntnt End -->
							</div>
						</div>
					</div>
				</div>
				<div class="bdHPd"></div>
			</div>
		</div>
		<jsp:include page="/jsps/footer.jsp" ></jsp:include>
	</body>

</html>