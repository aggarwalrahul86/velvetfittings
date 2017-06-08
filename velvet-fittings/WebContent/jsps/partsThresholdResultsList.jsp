
<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>

<c:if test="${not empty partsSearch.partsSearchResults}">

		<div class="bdHPd"></div>
		<div class="inrPgBdCntntBorder">
			<div class="inrPgTbCntntTtl">Parts Search Results</div>
			<div class="inrPgTbCntntTtlLn"></div>
			<div class="bdHPd"></div>
			<div class="tblWrpr" style="width:auto">
			<table cellspacing="1" cellpadding="0" border="0" class="tablesorter tableSorterFixed" 
				id="partThresholdTable">
				<thead>
					<tr>
						<th class="header">Created Date</th>
						<th class="header">Parts Name</th>
						<th class="header">Parts Code</th>
						<th class="header">Threshold Quantity</th>
						<th class="header">Available Quantity</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="partsSearchBean" items="${partsSearch.partsSearchResults}" varStatus="count">
						<tr>
							<td><c:out value="${partsSearchBean.createdOn}"></c:out></td>
							<td><c:out value="${partsSearchBean.partsName}"></c:out></td>
							<td><c:out value="${partsSearchBean.partsCode}"></c:out></td>
							<td><c:out value="${partsSearchBean.thresholdQuantity}"></c:out></td>
							<td><c:out value="${partsSearchBean.partsQuantity}"></c:out></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			</div>
			<div class="bdHPd"></div>
		</div>
		<div class="bdHPd"></div>
</c:if>