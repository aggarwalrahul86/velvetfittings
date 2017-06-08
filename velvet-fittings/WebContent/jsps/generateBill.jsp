
<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>
<%@ taglib prefix="fn" uri="/fn"%>
<%
	String contextPath = request.getContextPath();
%>


<div class="bdHPd"></div>
<div  id="messageDiv"><jsp:include page="/jsps/messageContainer.jsp" ></jsp:include></div>

<div class="inrPgBdCntntWrpr" id="tbLnkCntnt_EdtPrgrm">
	<form:form commandName="bill" action=""
		method="POST" name="generateBillForm" id="generateBillForm">
	<input type ="hidden" id="billId" name="billId" value="${bill.billNo}" />
		<div id="inrPgBdCntntSearchProgram">
			<div class="inrPgBdCntntBorder">

				<div class="inrPgTbCntntTtl">Generate Bill</div>
				<div class="inrPgTbCntntTtlLn"></div>
				<div class="bdHPd"></div>

				<div style="width:100%;	height:2px; overflow:hidden;"></div>
				<div class="edtActLbl">
					<label>Order</label>
				</div>
				<div class="inrPgBdElmt">
					<form:select path="order.orderId"
						cssClass="vlvtSelect" id="orderId" title="Order" onchange="getOrderInfo()">
						<form:option value="-1" label="-- Select --" />
						<form:options items="${orderList}"
							itemValue="code" itemLabel="name" />
					</form:select>
				</div>
				<div class="clrFlts"></div>
				<div id="SubOrderSection" style="display:none">
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Client Name</label>
				</div>
				<div class="inrPgBdElmt" style="height: 15px; width: 252px;">
					${bill.order.clientName}
				</div>
				<div class="edtActMdPd"></div>
				<div class="edtActLbl" style="padding-top:0px;">
					<label>Order Date</label>
				</div>
				<div class="inrPgBdElmt" style="height: 15px; width: 252px;">
					${bill.order.orderDate}
				</div>	
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				<c:if test="${not empty bill.order.itemsList}">
				<input type="hidden" id="totalItemsCount"
							name="totalItemsCount"
							value="${fn:length(bill.order.itemsList)}"></input>
				<div id="tableSection">	
				<table cellspacing="1" cellpadding="0" border="0" class="tablesorter tableSorterFixed"
					id="itemTransactionTable" style="width: 100%;">
					<thead>
						<tr>
								<th style="width:10%;text-align:center" class="header">Sl No.</th>
								<th style="width:40%;text-align:center" class="header">Item Name</th>
								<th style="width:15%;text-align:center" class="header">Price</th>
								<th style="width:15%;text-align:center" class="header">Quantity</th>
								<th style="width:20%;text-align:center" class="header">Total Price</th>																		
						</tr>
					</thead>
					<tbody>	
							<c:forEach items="${bill.order.itemsList}" var="issue" varStatus="rowItem">
								<tr>
									<td style="width:10%;text-align:center">${rowItem.index + 1}</td>
									<td style="width:40%;text-align:center">
										${issue.itemName }
									</td>
									<td style="width:15%;text-align:center">${issue.itemPrice }
									</td>
									<td style="width:15%;text-align:center">
									${issue.transactedQnty }

									</td>
									<td style="width:20%;text-align:center">
									
									<input type="hidden" id ="itemsListTransactedPrice${rowItem.index}" name="itemsListTransactedPrice${rowItem.index}" value="${issue.transactedPrice }" />

									${issue.transactedPrice }

									</td>
								</tr>
	
							</c:forEach>
							<tr>
								<td colspan="4" style="text-align:right;line-height: 25px;">Total Price</td>
								<td colspan="1">
								<form:input path="totalAmnt" id="totalQuantityPrice" title="Total Price of Items" cssClass="vlvtSmallTbleTextBox" onchange="calculateNetAmount()"/>
								</td>
							</tr>
							<tr>
								<td colspan="1" style="text-align:left;">Discount Percentage(%)</td>
								
								<td colspan="1">
									<form:input path="dscntAmnt" id="totalDiscountAmount" title="Total Discount on Items" cssClass="vlvtSmallTbleTextBox" onchange="calculateNetAmount()" />
								</td>
								<td colspan="1"></td>
								<td colspan="1" style="text-align:right;line-height: 25px;">Discount Amount</td>
								<td colspan="1" style="text-align:right;line-height: 25px;"><form:input path="discountAmount" id="discountAmount" readonly="true" title="Discount Amount" cssClass="vlvtSmallTbleTextBox"/></td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:right;line-height: 25px;">Tax Amount</td>
								<td colspan="1">
								<form:input path="taxAmnt" id="totalTaxAmount" title="Total Tax on Items" cssClass="vlvtSmallTbleTextBox" onchange="calculateNetAmount()"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:right;line-height: 25px;">Net Amount</td>
								<td colspan="1">
								<form:input path="netAmount" id="totalNetAmount" readonly="true" title="Net Amount on Items" cssClass="vlvtSmallTbleTextBox"/>
								</td>
							</tr>
							<tr>
								<td colspan="4" style="text-align:right;line-height: 25px;">Bill Date</td>
								<td colspan="1">
								<form:input path="billDate" id="billDate" readonly="true" title="Bill Date" cssClass="vlvtSmallCalTbleTextBox"/>
								</td>
							</tr>
					</tbody>
				
				</table>
				</div>	
				</c:if>
				<div class="clrFlts"></div>
				<div class="bdHPd"></div>
				<div style="margin:10px auto;">
					<div id="generateButonSection" style="display:none;margin:0px auto; width: 292px;" >
						<div class="btnCntnrNew btnBkGrndGrdnt" id="btnCntnrId" style="width:100px;float:left;"
						onclick="cancelOrderForItems()">Cancel Order</div>
						<div class="edtActMdPd"></div>
						<div class="btnCntnrNew btnBkGrndGrdnt" id="btnCntnrId" style="width:100px;float:left;"
							onclick="generateBillDetails()">Generate Bill</div>
						<div class="clrFlts"></div>
					</div>
					
					<div class="btnCntnrNew btnBkGrndGrdnt" id="viewBillButton" style="width:100px;margin:0px auto;"
						onclick="viewBillDetails()">View Bill</div>
				</div>
				
				<div class="clrFlts"></div>
				</div>
			</div>
			<div class="bdHPd"></div>
		</div>

	</form:form>

</div>
