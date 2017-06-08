
<%@ page import="com.velvet.vo.UserBean"%> 
<%@ page import="com.velvet.constants.IActionConstants"%>
<%@ page import="com.velvet.web.SessionManager"%>
<%@ page import="com.velvet.vo.navigation.TabNavigationBean"%>
<%@ page import="com.velvet.vo.navigation.RoleAccessBean"%>
<%@ page import="com.velvet.vo.navigation.MenuNavigationBean"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<%
	Map<String, TabNavigationBean> tabAssociations = null;
	Map<String, MenuNavigationBean> menuAssociations = null;
	String landingMenuId = null; 
	
	HashMap<String, Object> sessionMap = SessionManager.getSessionMap(request);
	UserBean userBean = null;
	if(sessionMap!=null){
		userBean = (UserBean)sessionMap.get(IActionConstants.USER_BEAN);
	}
	
	if(userBean!=null){
		RoleAccessBean roleAccessBean = userBean.getRoleAccessBean();
		if(roleAccessBean!=null){
			tabAssociations = roleAccessBean.getTabAssociations();
			if(tabAssociations!=null){
				TabNavigationBean tabNavigationBean = tabAssociations.get(IActionConstants.TAB_ADMIN);
				landingMenuId = tabNavigationBean.getLandingMenuId();
				menuAssociations = tabNavigationBean.getMenuAssociations();
			}
		}
	}
%>


<div class="bdHPd"></div>
<div class="inrPageLeftMenuTextWrpr">
	<form method="get" name="leftmenuForm" id="leftmenuForm" action="">
		<div class="inrPageLeftMenuTextDiv nrmlTxt">
			<ul>
				<%
					if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_ADD_DELETE_PARTS)){
						MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_ADD_DELETE_PARTS);
						String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_AddDeletePrts" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<%
					}if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_MANAGE_ITEMS)){
						MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_MANAGE_ITEMS);
						String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_MngItems" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<%
					}if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_GENERATE_BILL)){
						MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_GENERATE_BILL);
						String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_GnrtBill" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<%
					}if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_PARTS_REPORT)){
						MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_PARTS_REPORT);
						String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_invReports" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<%
					}if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_PARTS_THRESHOLD_REPORT)){
						MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_PARTS_THRESHOLD_REPORT);
						String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_thresholdReports" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<% }if(menuAssociations!=null && menuAssociations.containsKey(IActionConstants.MENU_ORDER_REPORT)){
					MenuNavigationBean menuNavigationBean = menuAssociations.get(IActionConstants.MENU_ORDER_REPORT);
					String navigationUrl = menuNavigationBean.getMenuNavigationUrl();
				%>
				<li><a id="tbLnk_orderReport" href="javascript:void(0);" class="leftMenuLink"
					onclick="menuClickHandler(this.id, '<%=navigationUrl%>')"
					title="<%=menuNavigationBean.getMenuName()%>"><span class="t"><%=menuNavigationBean.getMenuName()%></span>
				</a>
				</li>
				<% } %>

			</ul>
		</div>
		<% 
			if(IActionConstants.MENU_ADD_DELETE_PARTS.equalsIgnoreCase(landingMenuId)){
		%>
			<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_AddDeletePrts" />
		<%
			}else if(IActionConstants.MENU_ADD_DELETE_PARTS.equalsIgnoreCase(landingMenuId)){
		%>
			<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_MngItems" />
		<%
			}else if(IActionConstants.MENU_GENERATE_BILL.equalsIgnoreCase(landingMenuId)){
		%>
			<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_GnrtBill" />
		<%
			}else if(IActionConstants.MENU_PARTS_REPORT.equalsIgnoreCase(landingMenuId)){
		%>
			<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_invReports" />
		<%
			}else if(IActionConstants.MENU_PARTS_THRESHOLD_REPORT.equalsIgnoreCase(landingMenuId)){
		%>
		<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_thresholdReports" />
		<%
			}else if(IActionConstants.MENU_ORDER_REPORT.equalsIgnoreCase(landingMenuId)){
		%>
		<input type="hidden" name="landingMenu" id="landingMenu" value="tbLnk_orderReport" />
		<%
			}
		%>
	</form>
</div>
