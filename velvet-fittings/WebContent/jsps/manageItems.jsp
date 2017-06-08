<%@ page import="com.velvet.vo.ManageItemOrderBean"%>
<%@ page import="com.velvet.vo.ItemOrderBean"%>
<%@ page import="com.velvet.vo.NameValuePairBean"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.Map"%>
<%@ page import="java.util.LinkedHashMap"%>

<%@ taglib prefix="c" uri="/c"%>
<%@ taglib prefix="form" uri="/form"%>
<%@ taglib prefix="spring" uri="/spring"%>
<%@ taglib prefix="fmt" uri="/fmt"%>



<%
	String contextPath = request.getContextPath();
	ManageItemOrderBean manageItemOrderBean = (ManageItemOrderBean)request.getAttribute("manageItemOrder"); 
	List<ItemOrderBean> addedItems = manageItemOrderBean.getAddedItems();
%>
<div class="bdHPd"></div>
<div  id="messageDiv"><jsp:include page="/jsps/messageContainer.jsp" ></jsp:include></div>

<div class="inrPgBdCntntWrpr" id="tbLnkCntnt_EdtPrgrm">
	<input type="hidden" name="PAGE_IDENTIFIER" id="PAGE_IDENTIFIER" value="${PAGE_IDENTIFIER}"/>
	<form:form commandName="manageItemOrder" action=""
		method="POST" name="manageItemsForm" id="manageItemsForm">
		<div id="inrPgBdCntntSearchProgram">
			<div class="inrPgTbCntntTtl">Manage Items</div>
			<div class="inrPgTbCntntTtlLn"></div>
			<div class="bdHPd"></div>
			<input type="hidden" id="allAddedItems" name="allAddedItems" value="<%=((addedItems.size()-1)<0) ? 0 : (addedItems.size()-1)%>"></input>
			<div id="addedItemTmpltCntnr" >
				<div id="inrPgBdCntntBorderWrpr_SECTIONINDEX" style="display: none;">
					<input type="hidden" id="isDeleted_SECTIONINDEX" name="addedItems[SECTIONINDEX].isDeleted" value="N"></input>
					<div class="inrPgBdCntntBorder" id="inrPgBdCntntBorder_SECTIONINDEX" style="width:730px;float:left;margin-top: 5px;">
						<div id="mngItmItmSearchCntnr_SECTIONINDEX" style="display: none;padding-left:375px">
							<div class="edtActLbl" style="width: 94px;">
								<label>Item Search</label>
							</div>
							<div class="inrPgBdElmt">
								<input type="text" class="vlvtTextBox" title="Category Search"
									id="itmSearchTextBoxId_SECTIONINDEX" 
									onkeyup="showAutosugstItems('itmAutoSgstNmeId_SECTIONINDEX','<%=contextPath%>/items/fetchItemsAutoSuggest.htm','itmSearchTextBoxId_SECTIONINDEX','itemId_SECTIONINDEX','categoryId_SECTIONINDEX')" 
									onclick="handleAutoSugstClick()" />
								<div class="clrFlts"></div>	
								<div id="itmAutoSgstNmeWrprId_SECTIONINDEX" class="edtPrjAutosuggest autosuggestWrpr">
									<div id="itmAutoSgstNmeId_SECTIONINDEX" class="autosugstDiv">
									</div>
								</div>
							</div>
							<div class="clrFlts"></div>
						</div>
						<div class="clrFlts"></div>
						<div class="edtActLbl" style="width: 90px;">
							<label>Category</label>
						</div>
						<div class="inrPgBdElmt">
							<select  class="vlvtSelect" name="addedItems[SECTIONINDEX].category" 
								id="categoryId_SECTIONINDEX" title="Category" onchange="populateItemsForCategory('categoryId_SECTIONINDEX', 'itemId_SECTIONINDEX')">
								<option value="-1" title="-- Select --" >-- Select --</option>
								<%
								List<NameValuePairBean> tmpltCategoryList = (List<NameValuePairBean>)request.getAttribute("categoryList");
								if(tmpltCategoryList!=null){
									for(NameValuePairBean nameValuePairBean : tmpltCategoryList){
								%>
									<option value="<%=nameValuePairBean.getCode()%>"
										title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
								<%
									}
								}
								%>
							</select>
						</div>
						<div class="edtActMdPd"></div>
						<div class="edtActLbl"  style="width: 90px;">
							<label>Item</label>
						</div>
						<div class="inrPgBdElmt">
							<select class="vlvtSelectSmall" name="addedItems[SECTIONINDEX].item" 
								id="itemId_SECTIONINDEX" title="Item">
								<option value="-1" title="-- Select --" >-- Select --</option>
								<%
								List<NameValuePairBean> tmpltItemList = (List<NameValuePairBean>)request.getAttribute("itemList");
								if(tmpltItemList!=null){
									for(NameValuePairBean nameValuePairBean : tmpltItemList){
								%>
									<option value="<%=nameValuePairBean.getCode()%>"
										title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
								<%
									}
								}
								%>
							</select>
						</div>
						<div onclick="openItemAutosuggest('mngItmItmSearchCntnr_SECTIONINDEX');" class="edtActSignumSrchHldr">
							<img src="../includes/images/search_icon.gif"></img>
						</div>
						<div class="clrFlts"></div>
						<div class="edtActLbl"  style="width: 90px;">
							<label>Quantity</label>
						</div>
						<div class="inrPgBdElmt">
							<input type="text" class="vlvtTextBox" title="Quantity"
								name="addedItems[SECTIONINDEX].quantity" id="itmQty_SECTIONINDEX" />
						</div>
						<div class="clrFlts"></div>
					</div>
					<div class="manageItemsAddDltBtnCntnr">
						<img class="manageItemsAddDltBtn" onclick="deleteItemRow('SECTIONINDEX');" id="issueHeaderDeleteButtonId"
							title="Delete Item" src="<%=contextPath%>/includes/images/delete.png"></img>
					</div>
					<div class="clrFlts"></div>
				</div>
			</div>
			<div id="addedItemsCntnr" >
				<%
					if(addedItems!=null && addedItems.size()>0){	
						for(int addedItemsCnt=0; addedItemsCnt<addedItems.size(); addedItemsCnt++){
							ItemOrderBean addedItem = addedItems.get(addedItemsCnt);
				%>
							<div id="inrPgBdCntntBorderWrpr_<%=addedItemsCnt%>" >
								<input type="hidden" id="isDeleted_<%=addedItemsCnt%>" name="addedItems[<%=addedItemsCnt%>].isDeleted" value="N"></input>
								<div class="inrPgBdCntntBorder" id="inrPgBdCntntBorder_<%=addedItemsCnt%>" style="width:730px;float:left;margin-top: 5px;">
									<div id="mngItmItmSearchCntnr_<%=addedItemsCnt%>" style="display: none;padding-left:375px">
										<div class="edtActLbl" style="width: 94px;">
											<label>Item Search</label>
										</div>
										<div class="inrPgBdElmt">
											<input type="text" class="vlvtTextBox" title="Category Search"
												id="itmSearchTextBoxId_<%=addedItemsCnt%>" 
												onkeyup="showAutosugstItems('itmAutoSgstNmeId_<%=addedItemsCnt%>','<%=contextPath%>/items/fetchItemsAutoSuggest.htm','itmSearchTextBoxId_<%=addedItemsCnt%>','itemId_<%=addedItemsCnt%>','categoryId_<%=addedItemsCnt%>')" 
												onclick="handleAutoSugstClick()" />
											<div class="clrFlts"></div>	
											<div id="itmAutoSgstNmeWrprId_<%=addedItemsCnt%>" class="edtPrjAutosuggest autosuggestWrpr">
												<div id="itmAutoSgstNmeId_<%=addedItemsCnt%>" class="autosugstDiv">
												</div>
											</div>
										</div>
										<div class="clrFlts"></div>
									</div>
									<div class="clrFlts"></div>
									<div class="edtActLbl" style="width: 90px;">
										<label>Category</label>
									</div>
									<div class="inrPgBdElmt">
										<select  class="vlvtSelect" name="addedItems[<%=addedItemsCnt%>].category"
											id="categoryId_<%=addedItemsCnt%>" title="Category" onchange="populateItemsForCategory('categoryId_<%=addedItemsCnt%>', 'itemId_<%=addedItemsCnt%>')">
											<option value="-1" title="-- Select --" >-- Select --</option>
											<%
											List<NameValuePairBean> categoryList = (List<NameValuePairBean>)request.getAttribute("categoryList");
											if(categoryList!=null){
												for(NameValuePairBean nameValuePairBean : categoryList){
													String selected = nameValuePairBean.getCode().equals(addedItem.getCategory()) ? "selected" : "";
											%>
												<option value="<%=nameValuePairBean.getCode()%>" <%=selected%> 
													title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
											<%
												}
											}
											%>
										</select>
									</div>
									<div class="edtActMdPd"></div>
									<div class="edtActLbl"  style="width: 90px;">
										<label>Item</label>
									</div>
									<div class="inrPgBdElmt">
										<select class="vlvtSelectSmall" name="addedItems[<%=addedItemsCnt%>].item" 
											id="itemId_<%=addedItemsCnt%>" title="Item">
											<option value="-1" title="-- Select --" >-- Select --</option>
											<%
											List<NameValuePairBean> itemList = (List<NameValuePairBean>)request.getAttribute("itemList");
											if(itemList!=null){
												for(NameValuePairBean nameValuePairBean : itemList){
													String selected = nameValuePairBean.getCode().equals(addedItem.getItem()) ? "selected" : "";
											%>
												<option value="<%=nameValuePairBean.getCode()%>" <%=selected%> 
													title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
											<%
												}
											}
											%>
										</select>
									</div>
									<div onclick="openItemAutosuggest('mngItmItmSearchCntnr_<%=addedItemsCnt%>');" class="edtActSignumSrchHldr">
										<img src="../includes/images/search_icon.gif"></img>
									</div>
									<div class="clrFlts"></div>
									<div class="edtActLbl"  style="width: 90px;">
										<label>Quantity</label>
									</div>
									<div class="inrPgBdElmt">
										<input type="text" class="vlvtTextBox" title="Quantity" value="<%=addedItem.getQuantity()%>"
											name="addedItems[<%=addedItemsCnt%>].quantity" id="itmQty_<%=addedItemsCnt%>" />
									</div>
									<div class="clrFlts"></div>
								</div>
								<div class="manageItemsAddDltBtnCntnr">
									<%
										if(addedItemsCnt==0){
									%>
										<img class="manageItemsAddDltBtn" onclick="addNewItemRow();"
												title="Add Item" src="<%=contextPath%>/includes/images/add.png"></img>
									<%
										}else{
									%>
										<img class="manageItemsAddDltBtn" onclick="deleteItemRow('<%=addedItemsCnt%>');" id="issueHeaderDeleteButtonId"
												title="Delete Item" src="<%=contextPath%>/includes/images/delete.png"></img>
									<%
										}
									%>
								</div>
								<div class="clrFlts"></div>
							</div>
				
				<%
						}
					}else{
				%>
							<div id="inrPgBdCntntBorderWrpr_0" >
							<input type="hidden" id="isDeleted_0" name="addedItems[0].isDeleted" value="N"></input>
							<div class="inrPgBdCntntBorder" id="inrPgBdCntntBorder_0" style="width:730px;float:left;">
								<div id="mngItmItmSearchCntnr_0" style="display: none;padding-left:375px">
									<div class="edtActLbl" style="width: 94px;">
										<label>Item Search</label>
									</div>
									<div class="inrPgBdElmt">
										<input type="text" class="vlvtTextBox" title="Category Search"
											id="itmSearchTextBoxId_0" 
											onkeyup="showAutosugstItems('itmAutoSgstNmeId_0','<%=contextPath%>/items/fetchItemsAutoSuggest.htm','itmSearchTextBoxId_0','itemId_0','categoryId_0')" 
											onclick="handleAutoSugstClick()">
										<div class="clrFlts"></div>	
										<div id="itmAutoSgstNmeWrprId_0" class="edtPrjAutosuggest autosuggestWrpr">
											<div id="itmAutoSgstNmeId_0" class="autosugstDiv">
											</div>
										</div>
									</div>
									<div class="clrFlts"></div>
								</div>
								<div class="clrFlts"></div>
								<div class="edtActLbl" style="width: 90px;">
									<label>Category</label>
								</div>
								<div class="inrPgBdElmt">
									<select  class="vlvtSelect" name="addedItems[0].category"
										id="categoryId_0" title="Category" onchange="populateItemsForCategory('categoryId_0', 'itemId_0')">
										<option value="-1" title="-- Select --" >-- Select --</option>
										<%
										List<NameValuePairBean> categoryList = (List<NameValuePairBean>)request.getAttribute("categoryList");
										if(categoryList!=null){
											for(NameValuePairBean nameValuePairBean : categoryList){
										%>
											<option value="<%=nameValuePairBean.getCode()%>"
												title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
										<%
											}
										}
										%>
									</select>
								</div>
								<div class="edtActMdPd"></div>
								<div class="edtActLbl"  style="width: 90px;">
									<label>Item</label>
								</div>
								<div class="inrPgBdElmt">
									<select class="vlvtSelectSmall" name="addedItems[0].item" 
										id="itemId_0" title="Item">
										<option value="-1" title="-- Select --" >-- Select --</option>
										<%
										List<NameValuePairBean> itemList = (List<NameValuePairBean>)request.getAttribute("itemList");
										if(itemList!=null){
											for(NameValuePairBean nameValuePairBean : itemList){
										%>
											<option value="<%=nameValuePairBean.getCode()%>"
												title="<%=nameValuePairBean.getName()%>" ><%=nameValuePairBean.getName()%></option>
										<%
											}
										}
										%>
									</select>
								</div>
								<div onclick="openItemAutosuggest('mngItmItmSearchCntnr_0');" class="edtActSignumSrchHldr">
									<img src="../includes/images/search_icon.gif"></img>
								</div>
								<div class="clrFlts"></div>
								<div class="edtActLbl"  style="width: 90px;">
									<label>Quantity</label>
								</div>
								<div class="inrPgBdElmt">
									<input type="text" class="vlvtTextBox" title="Quantity"
										name="addedItems[0].quantity" id="itmQty_0" />
								</div>
								<div class="clrFlts"></div>
							</div>
							<div class="manageItemsAddDltBtnCntnr">
								<img class="manageItemsAddDltBtn" onclick="addNewItemRow();"
									title="Add Item" src="<%=contextPath%>/includes/images/add.png"></img>
							</div>
							<div class="clrFlts"></div>
						</div>
				
				<%
					}
				%>
				
			</div>
			<div class="btnCntnrNew btnBkGrndGrdnt" id="btnCntnrId" style="width:130px;"
				onclick="checkAvailabilityForItems()">Check Availability</div>
		</div>
		
		<div id="partsAvailabilityStatsWrpr" style="display: none">
			<jsp:include page="partsAvailabilityList.jsp"></jsp:include>
		</div>
		
	</form:form>
</div>
<div class="bdHPd"></div>