
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
	<form:form commandName="parts" action=""
		method="POST" name="addDeletePartsForm" id="addDeletePartsForm">
	
		<div id="inrPgBdCntntSearchProgram">
			<div class="inrPgBdCntntBorder">

				<div class="inrPgTbCntntTtl">Add/Delete Parts</div>
				<div class="inrPgTbCntntTtlLn"></div>
				<div class="bdHPd"></div>

				<div class="edtActLbl">
					<label>Category</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="categoryId"
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
				<div style="width:100%;	height:2px; overflow:hidden;"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Parts</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="partsId" id="partsId" onchange="populateDependentpartsInfo('partsId','${contextPath}/parts/showPartsInfo')"
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
					<form:input path="name" id="name" cssClass="vlvtTextBox" readonly="true" />
				</div>
				<div class="clrFlts"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Part Code</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="code" id="code" cssClass="vlvtTextBox" readonly="true" />
				</div>
				<div class="edtActMdPd"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Available Quantity</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="quantity" id="quantity" cssClass="vlvtTextBox"  readonly="true" />
				</div>
				<div class="clrFlts"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Threshold Quantity</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="threshold" id="threshold" cssClass="vlvtTextBox" readonly="true" />
				</div>
				<div class="clrFlts"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Action</label>
				</div>
				<div class="inrPgBdElmt">
					<input type="radio" id="action" name="action" value="ADD" style="padding:2px 0px;" onclick="showAddPartsSection()"/><span style="padding:0px 5px;">Add Parts</span>
					<input type="radio" id="action" name="action" value="DELETE" style="padding:2px 0px;" onclick="showDeletePartsSection()"/><span style="padding:0px 5px;">Delete Parts</span>
				</div>
				<div class="clrFlts"></div>
				<div id="addPartsSctn" style="display:none;">
					<div class="edtActLbl" style="padding-top:0px;">
						<label>Quantity to be Added</label>
					</div>
					<div class="inrPgBdElmt">
						<input type="text" id="addedQnty" name="addedQnty" class="vlvtTextBox" title="Quantity to be Added" />
					</div>
					
				</div>
				<div id="deletePartsSctn" style="display:none;">
					<div class="edtActLbl" style="padding-top:0px;">
						<label>Quantity to be Deleted</label>
					</div>
					<div class="inrPgBdElmt">
						<input type="text" id="deleteQnty" name="deleteQnty" class="vlvtTextBox"  title="Quantity to be Deleted"/>
					</div>
				</div>
				<div class="clrFlts"></div>
				<div id="dateSection" style="display:none;">
				<div class="edtActLbl" style="padding-top:0px;">
						<label>Updated Date</label>
					</div>
					<div class="inrPgBdElmt">
						<input type="text" id="updatedDate" name="updatedDate" class="vlvtTextBox"  title="Date For Order"/>
					</div>
				</div>
				<div id="addBtnSection" style="display:none;">
					<div class="edtActMdPd"></div>
					<div class="edtActBtnCntnr" style="float:left; width:auto;">
						<div class="btnCntnrWrpr">
							<div class="btnCntnr" id="btnCntnrId"
								onclick="addPartsToInventory()">
								<div class="btnBkGrndGrdnt btnCntnrInr" id="btnCntnrInrId">
									<div class="btnCntnrText">Add Parts</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div id="deleteBtnSection" style="display:none;">
					<div class="edtActMdPd"></div>
					<div class="edtActBtnCntnr" style="float:left; width:auto;">
						<div class="btnCntnrWrpr">
							<div class="btnCntnr" id="btnCntnrId"
								onclick="deletePartsToInventory()">
								<div class="btnBkGrndGrdnt btnCntnrInr" id="btnCntnrInrId">
									<div class="btnCntnrText">Delete Parts</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				
			</div>
		</div>

	</form:form>

</div>
<div class="bdHPd"></div>