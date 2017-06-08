
<%@ page import="com.velvet.vo.PartAvailabilityBean"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.Iterator"%>

<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>

<div class="bdHPd"></div>
<%
	Map<String, PartAvailabilityBean> partAvailabilityMap = (Map<String, PartAvailabilityBean>)request.getAttribute("partAvailabilityMap");
	if(partAvailabilityMap!=null && !partAvailabilityMap.isEmpty()){
		String orderAvailability = (String)request.getAttribute("orderAvailability");
%>
		<%
			if("Y".equals(orderAvailability)){
		%>
				<div class="msgCntnr successMsg" id="placeOrdrMsgCntnr" >Sufficient stocks present. Generate order for the item list by clicking on 'Place Order'.</div>
		<%
			}else{
		%>
				<div class="msgCntnr errorMsg" id="placeOrdrMsgCntnr" >Sufficient stocks not present. Order cannot be placed for the item list.</div>
		<%	
			}
		%>
		<div class="inrPgBdCntntBorder">
			<div class="inrPgTbCntntTtl">Availability Statistics</div>
			<div class="inrPgTbCntntTtlLn"></div>
			<div class="bdHPd"></div>
			<div class="tblWrpr" style="overflow-x:auto;">
				<table cellspacing="1" cellpadding="0" border="0" class="tablesorter tableSorterFixed"
					id="searchProjectSSATResultsTbl" style="width: 100%;">
					<thead>
						<tr>
							<th class="header">Part Code</th>
							<th class="header">Part Name</th>
							<th class="header">Required Quantity</th>
							<th class="header">Current Quantity</th>
							<th class="header">Threshold</th>
						</tr>
					</thead>
					<tbody>
					<%
						
					for (Iterator<String> partAvailabilityMapItr = partAvailabilityMap.keySet().iterator(); partAvailabilityMapItr.hasNext();) {
						PartAvailabilityBean partAvailabilityBean = partAvailabilityMap.get(partAvailabilityMapItr.next());
					%>
						<tr> 
							<td><%=partAvailabilityBean.getPartCode()%></td>
							<td><%=partAvailabilityBean.getPartName()%></td>
							<td>
								<%
									String displayStyle="successMsg";
									if(partAvailabilityBean.isInsufficientParts()){
										displayStyle="errorMsg";
									}
								%>
								<span class="<%=displayStyle%>" ><%=partAvailabilityBean.getRequiredQuantity()%></span>
							</td>
							<td><%=partAvailabilityBean.getCurrentQuantity()%></td>
							<td><%=partAvailabilityBean.getThreshold()%></td>
						</tr>
					<%
					}
					%>
					</tbody>
				</table>
			</div>
			<div class="bdHPd"></div>
		</div>
		<div class="bdHPd"></div>
		
		<%
			if("Y".equals(orderAvailability)){
		%>
				<div class="bdHPd"></div>
				<div class="btnCntnrNew btnBkGrndGrdnt" id="btnCntnrId" style="width:130px;"
					onclick="updateOrderForItems()">Update Order</div>
				<div class="bdHPd"></div>
				
		<%
			}
		%>
<%	
	}
%>
