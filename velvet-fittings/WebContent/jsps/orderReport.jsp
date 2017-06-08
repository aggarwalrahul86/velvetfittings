
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
	<input type="hidden" name="PAGE_IDENTIFIER" id="PAGE_IDENTIFIER" value="${PAGE_IDENTIFIER}"/>
	<form:form commandName="orderReport" action=""
		method="POST" name="orderReportForm" id="orderReportForm">
		
		<form:hidden path="orderId" id="orderId" />
		<div id="inrPgBdCntntSearchProgram">
			<div class="inrPgBdCntntBorder">

				<div class="inrPgTbCntntTtl">Order Search Screen</div>
				<div class="inrPgTbCntntTtlLn"></div>
				<div class="bdHPd"></div>
				<div id="ordrRptItmSearchCntnr" style="display: none;float: left; width: 401px;min-height: 10px;">
					<div class="edtActLbl" id="ordrRptItmSearchLbl" style="display: none;">
						<label>Item Search</label>
					</div>
					<div class="inrPgBdElmt" id="ordrRptItmSearchTxtCntnr" style="display: none;">
						<input type="text" class="vlvtTextBox" title="Item Search"
							id="ordrRptItmSearchTextBoxId" 
							onkeyup="showAutosugstItems('ordrRptItmSrchAutoSgstNmeId','<%=contextPath%>/items/fetchItemsAutoSuggest.htm','ordrRptItmSearchTextBoxId','orderSearchBeanItem','')" 
							onclick="handleAutoSugstClick()" />
						<div class="clrFlts"></div>	
						<div id="ordrRptItmSrchAutoSgstNmeWrprId" class="edtPrjAutosuggest autosuggestWrpr">
							<div id="ordrRptItmSrchAutoSgstNmeId" class="autosugstDiv">
							</div>
						</div>
					</div>
					<div class="clrFlts"></div>
				</div>
				<div id="ordrRptClntSearchCntnr" style="display: none;float: left; width: 381px;">
					<div class="edtActLbl">
						<label>Client Search</label>
					</div>
					<div class="inrPgBdElmt">
						<input type="text" class="vlvtTextBox" title="Client Search"
							id="ordrRptClntSearchTextBoxId" 
							onkeyup="showAutosugstClients('ordrRptClntSrchAutoSgstNmeId','<%=contextPath%>/items/fetchClientsAutoSuggest.htm','ordrRptClntSearchTextBoxId','orderSearchBeanClient')" 
							onclick="handleAutoSugstClick()" />
						<div class="clrFlts"></div>	
						<div id="ordrRptClntSrchAutoSgstNmeWrprId" class="edtPrjAutosuggest autosuggestWrpr">
							<div id="ordrRptClntSrchAutoSgstNmeId" class="autosugstDiv">
							</div>
						</div>
					</div>
					<div class="clrFlts"></div>
				</div>
				<div class="clrFlts"></div>
				<div class="edtActLbl">
					<label>Item</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="orderSearchBean.item" cssStyle="width:231px;"
						cssClass="vlvtSelect" id="orderSearchBeanItem" title="Item" >
						<form:option value="-1" label="-- Select --" />
						<form:options items="${itemList}"
							itemValue="code" itemLabel="name" />
					</form:select>
				</div>
				<div onclick="openItemAutosuggest('ordrRptItmSearchCntnr');" class="edtActSignumSrchHldr">
					<img src="../includes/images/search_icon.gif"></img>
				</div>
				<div class="edtActMdPd"></div>
				<div class="edtActLbl" >
					<label>Client Name</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="orderSearchBean.clientName" cssStyle="width:231px;"
						cssClass="vlvtSelect" id="orderSearchBeanClient" title="Client" >
						<form:option value="-1" label="-- Select --" />
						<form:options items="${clientList}"
							itemValue="code" itemLabel="name" />
					</form:select>
				</div>
				<div onclick="openClientAutosuggest('ordrRptClntSearchCntnr', 'ordrRptItmSearchCntnr');" 
					class="edtActSignumSrchHldr">
					<img src="../includes/images/search_icon.gif"></img>
				</div>
				<div class="clrFlts"></div>
				<div class="edtActLbl">
					<label>Order From Date</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="orderSearchBean.orderFromDate" id="orderSearchBeanOrderFromDate" cssClass="vlvtTextBox" title="Order From Date" 
						cssStyle="width:200px;"/>
				</div>
				<div class="edtActMdPd" style="width:62px;"></div>
				<div class="edtActLbl">
					<label>Order To Date</label>
				</div>
				<div class="inrPgBdElmt">
					<form:input path="orderSearchBean.orderToDate" id="orderSearchBeanOrderToDate" cssClass="vlvtTextBox" title="Order To Date" 
						cssStyle="width:200px;"/>
				</div>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
					<div class="edtActBtnCntnr" style="width:130px;">
						<div class="btnCntnrWrpr">
							<div class="btnCntnr" id="btnCntnrId"
								onclick="getOrderSearchResults()">
								<div class="btnBkGrndGrdnt btnCntnrInr" id="btnCntnrInrId">
									<div class="btnCntnrText">Search</div>
								</div>
							</div>
						</div>
					</div>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				<div id="inrPgBdCntntOrderSearchResults" >
		             <jsp:include page="orderSearchResultList.jsp"></jsp:include>
	           	</div>
	           	<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				
				<div id="trsprtEditOrderOvrlOuterId" class="trsprtPiidOuter" style="display: none">
					<jsp:include page="editOrderOverlay.jsp"></jsp:include>
				</div>
			</div>
		</div>

	</form:form>

</div>
<div class="bdHPd"></div>