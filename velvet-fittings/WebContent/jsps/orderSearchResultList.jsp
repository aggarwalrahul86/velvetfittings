
<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>

<c:if test="${not empty orderReport.orderSearchResultBeanList}">
		<div class="bdHPd"></div>
		<div class="inrPgBdCntntBorder">
			<div class="inrPgTbCntntTtl">Order Search Results</div>
			<div class="inrPgTbCntntTtlLn"></div>
			<div class="bdHPd"></div>
			<div class="tblWrpr">
				<table cellspacing="1" cellpadding="0" border="0" class="tablesorter tableSorterFixed"
					id="orderSearchResultsTbl">
					<thead>
						<tr>
							<th class="header" style="width: 70px;">Order ID</th>
							<th class="header" style="width: 80px;">Order Date</th>
							<th class="header" style="width: 220px;">Order Name</th>
							<th class="header" style="width: 200px;">Client Name</th>
							<th class="header" style="width:60px;">Bill Id</th>
							<th class="header" style="width:70px;">Bill Date</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="orderSearchResultBean" items="${orderReport.orderSearchResultBeanList}" varStatus="count">
							<tr>
								<td style="width: 70px;"><c:out value="${orderSearchResultBean.orderId}"></c:out></td>
								<td style="width: 80px;"><c:out value="${orderSearchResultBean.orderDate}"></c:out></td>
								<td class="Itemname" style="width: 220px;"><a href="javascript:void(0);" title="${orderSearchResultBean.orderName}"
									onclick="getDetailsForOrderId('<c:out value="${orderSearchResultBean.orderId}"></c:out>')"><c:out
										value="${orderSearchResultBean.orderName}"></c:out> </a></td>
								<td style="width: 200px;"><c:out value="${orderSearchResultBean.clientName}"></c:out></td>
								<td style="width: 60px;">
									<c:if test="${not empty orderSearchResultBean.billId}">
										<a href="javascript:void(0);" title="View Bill <c:out value="${orderSearchResultBean.billId}"></c:out>"
											onclick="viewBill('<c:out value="${orderSearchResultBean.billId}"></c:out>')"><c:out 
												value="${orderSearchResultBean.billId}"></c:out></a>
									</c:if>
								</td>
								<td style="width: 70px;"><c:out value="${orderSearchResultBean.billDate}"></c:out></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			<div class="bdHPd"></div>
		</div>
		<div class="bdHPd"></div>
</c:if>