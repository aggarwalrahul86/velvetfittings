
<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>

<%
	String contextPath = request.getContextPath();
%>
<div class="bdHPd"></div>
<div  id="messageDiv"><jsp:include page="/jsps/messageContainer.jsp" ></jsp:include></div>

<div class="inrPgBdCntntWrpr" id="tbLnkCntnt_EdtPrgrm">
	<form:form commandName="partsSearch" action=""
		method="POST" name="addDeletePartsForm" id="addDeletePartsForm">
	
		<div id="inrPgBdCntntSearchProgram">
			<div class="inrPgBdCntntBorder">

				<div class="inrPgTbCntntTtl">Parts Threshold Search Screen</div>
				<div class="inrPgTbCntntTtlLn"></div>
				<div class="bdHPd"></div>

				<div class="edtActLbl">
					<label>Category</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="parts.categoryId"
						cssClass="vlvtSelect" id="categoryId" title="Category" onchange="populateDependentDropdownForParts('categoryId', '${contextPath}/parts/showPartsByCategory', 'partsId','categoryId');">
						<form:option value="-1" label="-- Select --" />
						<form:options items="${categoryList}"
							itemValue="code" itemLabel="name" />
					</form:select>
				</div>
				<div class="clrFlts"></div>
				<div id="editPartsSearchCntnr" style="display: none">
					
					<div class="sttsInsrtnLbl">
						<label>Parts Search</label>
					</div>
					<div class="sttsInsrtnMdPd" style="width:2%"></div>
					<div class="inrPgBdElmt">
						<input type="text" class="vlvtTextBox"
							id="partSearchTextBoxId"
							onkeyup="showAutosugstItems('editPartAutoSgstNmeId','${contextPath}/parts/getPartListByPattern.htm','partSearchTextBoxId','partsId','categoryId');"
							onclick="handleAutoSugstClick()">
						<div class="clrFlts"></div>	
						<div id="editPartAutoSgstWrprId" class="autosuggestWrpr" style="width: 252px;">
							<div id="editPartAutoSgstNmeId" class="autosugstGrdnt autosugstDiv">
							</div>
						</div>
					</div>
				</div>
				<div class="bdHPd"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Parts</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="parts.partsId" id="partsId" onchange="populateDependentpartsInfo('partsId','${contextPath}/parts/showPartsInfo')"
			 			title="Parts" cssClass="vlvtSelectSmall">
						<form:option value="-1" label="-- Select --" />
						<form:options items="${partList}"
							itemValue="code" itemLabel="name" />
					</form:select>
				</div>
				<div onclick="opensearchBoxAccount('editPartsSearchCntnr');" class="edtActSignumSrchHldr">
					<img src="../includes/images/search_icon.gif"></img>
				</div>
				<div class="edtActMdPd"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Part Name</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="parts.name" id="name" cssClass="vlvtTextBox" readonly="true" />
				</div>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
					<div class="edtActBtnCntnr" style="width:130px;">
						<div class="btnCntnrWrpr">
							<div class="btnCntnr" id="btnCntnrId"
								onclick="getPartThresholdResults()">
								<div class="btnBkGrndGrdnt btnCntnrInr" id="btnCntnrInrId">
									<div class="btnCntnrText">Search</div>
								</div>
							</div>
						</div>
					</div>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				<div id="inrPgBdCntntPartsSearchResults" >
		             <jsp:include page="partsThresholdResultsList.jsp"></jsp:include>
	           </div>
			</div>
		</div>

	</form:form>

</div>
<div class="bdHPd"></div>