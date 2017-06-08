<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Velvet-Fittings</title>
<jsp:include page="/jsps/commonMetaData.jsp"></jsp:include>
<jsp:include page="/jsps/commonScript.jsp"></jsp:include>
</head>
<body class="body" id="body">
	<%@ taglib prefix="c" uri="/c"%>
	<%@ taglib prefix="form" uri="/form"%>
	<%@ taglib prefix="spring" uri="/spring"%>
	<%@ taglib prefix="fmt" uri="/fmt"%>
	<%@ taglib prefix="fn" uri="/fn"%>
	<%
		String contextPath = request.getContextPath();
	%>


	<div class="bdHPd"></div>
	<div id="messageDiv"><jsp:include
			page="/jsps/messageContainer.jsp"></jsp:include></div>

	<div class="inrPgBdCntntWrpr" id="tbLnkCntnt_EdtPrgrm" style="width: 1000px; margin: 40px auto;">
		<form:form commandName="bill" action="" method="POST"
			name="viewBillForm" id="viewBillForm">
			<div id="inrPgBdCntntSearchProgram">
				<div class="inrPgBdCntntBorder">

					<div class="applnTitle" style="margin:12px auto;font-size: 36px;">Velvet-Fittings</div>
					<div class="color_line"></div>
					<div class="bdHPd"></div>
					<div style="margin:0 auto; width: 408px;">
						<div class="edtActLbl" style="width: 90px;font-size: 15px;">
							<label>Client Name</label>
						</div>
						<div class="edtActLbl" style="width: 280px;font-size: 15px;">
							${bill.order.clientName}
						</div>
						<div class="clrFlts"></div>
						<div class="edtActLbl" style="width: 90px;font-size: 15px;">
							<label>Bill No.</label>
						</div>
						<div class="edtActLbl" style="width: 90px;font-size: 15px;">
							${bill.billNo}
						</div>
						<div class="edtActMdPd"></div>
						<div class="edtActLbl" style="width: 90px;font-size: 15px;">
							<label>Bill Date</label>
						</div>
						<div class="edtActLbl" style="width: 90px;font-size: 15px;">
							${bill.billDate}
						</div>
						<div class="clrFlts"></div>
						<div class="bdHPd"></div>
					</div>
					<c:if test="${not empty bill.order.itemsList}">
						<input type="hidden" id="totalItemsCount" name="totalItemsCount"
							value="${fn:length(bill.order.itemsList)}"></input>
						<div id="tableSection">
							<table cellspacing="1" cellpadding="0" border="0"
								class="billtblSorter billtblSorterFixed" id="itemTransactionTable"
								style="width: 100%;">
								<thead>
									<tr>
										<th style="width: 10%; text-align: center" class="header">Sl
											No.</th>
										<th style="width: 40%; text-align: center" class="header">Item
											Name</th>
										<th style="width: 15%; text-align: center" class="header">Price</th>
										<th style="width: 15%; text-align: center" class="header">Quantity</th>
										<th style="width: 20%; text-align: center" class="header">Total
											Price</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach items="${bill.order.itemsList}" var="issue"
										varStatus="rowItem">
										<tr>
											<td style="width: 10%; text-align: center">${rowItem.index
												+ 1}</td>
											<td style="width: 40%; text-align: center">
												${issue.itemName }</td>
											<td style="width: 15%; text-align: center">${issue.itemPrice
												}</td>
											<td style="width: 15%; text-align: center">
												${issue.transactedQnty }</td>
											<td style="width: 20%; text-align: center"><input
												type="hidden" id="itemsListTransactedPrice${rowItem.index}"
												name="itemsListTransactedPrice${rowItem.index}"
												value="${issue.transactedPrice }" />

												${issue.transactedPrice }</td>
										</tr>

									</c:forEach>
									<tr>
										<td colspan="4" style="text-align: right">Total Price(INR)</td>
										<td colspan="1" style="text-align: center">
										${bill.totalAmnt}
										</td>
									</tr>
									<tr>
									<td colspan="1"style="text-align: left">Discount Percentage(%)</td>
									<td colspan="1" style="text-align: center">
										${bill.dscntAmnt}
										</td>
								
									<td colspan="1"></td>
									<td colspan="1" style="text-align: right">Discount Amount(INR)</td>
										<td colspan="1" style="text-align: center">
										${bill.discountAmount}
										</td>

									</tr>
									<tr>
										<td colspan="4" style="text-align: right">Tax Amount(INR)</td>
										<td colspan="1" style="text-align: center">
										${bill.taxAmnt}
										</td>
									</tr>
									<tr>
										<td colspan="4" style="text-align: right"><b>Net Amount(INR)</b></td>
										<td colspan="1" style="text-align: center"><b>
										${bill.totalAmnt + bill.taxAmnt - bill.dscntAmnt*0.01*bill.totalAmnt}
										</b>
										</td>
									</tr>
								</tbody>

							</table>
						</div>
					</c:if>
					<div class="clrFlts"></div>
					<div class="bdHPd"></div>
					<div class="bdHPd"></div>
				</div>
				<div class="btnCntnrNew btnBkGrndGrdnt" id="btnCntnrId" style="width:100px;margin:10px auto;"
						onclick="printBill()">Print</div>
			</div>

		</form:form>

	</div>
</body>
</html>
