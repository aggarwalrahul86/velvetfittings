<%@ page import="com.velvet.vo.UserBean"%> 
<%@ page import="com.velvet.constants.IActionConstants"%>
<%@ page import="com.velvet.web.SessionManager"%>
<%@ page import="com.velvet.vo.navigation.TabNavigationBean"%>
<%@ page import="com.velvet.vo.navigation.RoleAccessBean"%>
<%@ page import="java.util.HashMap"%>
<%@ page import="java.util.Map"%>

<%
	Map<String, TabNavigationBean> tabAssociations = null;
	String landingTabId = null; 
	String landingTabUrl = null;
	
	HashMap<String, Object> sessionMap = SessionManager.getSessionMap(request);
	UserBean userBean = null;
	if(sessionMap!=null){
		userBean = (UserBean)sessionMap.get(IActionConstants.USER_BEAN);
	}
	
	if(userBean!=null){
		RoleAccessBean roleAccessBean = userBean.getRoleAccessBean();
		if(roleAccessBean!=null){
			landingTabId = roleAccessBean.getLandingTabId(); 
			tabAssociations = roleAccessBean.getTabAssociations();
			if(tabAssociations!=null){
				TabNavigationBean tabNavigationBean = tabAssociations.get(landingTabId);
				if(tabNavigationBean!=null){
					landingTabUrl = tabNavigationBean.getTabNavigationUrl();
				}
			}
		}
	}
%>
<div id="lndnpgTabCntnrId">
	<div class="lndnpgTabLCntnr">
		<div class="lndnpgTabLPad" id="lndnpgTabLPadId"></div>
		<%
			if(tabAssociations!=null && tabAssociations.containsKey(IActionConstants.TAB_ADMIN)){
				TabNavigationBean tabNavigationBean = tabAssociations.get(IActionConstants.TAB_ADMIN);
				String navigationUrl = tabNavigationBean.getTabNavigationUrl();
		%>
		<div class="lndnpgTabWrpr" id="lndnpgTabWrprId">
			<div class="lndnpgTab" id="lndnpgAdminTabId" onclick="tabClickHandler(this.id, '<%=navigationUrl%>')">
				<div class="lndnpgTabText"><%=tabNavigationBean.getTabName()%></div>
			</div>
		</div>
		<div class="lndnpgTabMPad" id="lndnpgTabMPadId"></div>
		<%
			}
		%>
	</div>
	<div class="lndnpgTabRCntnr"></div>
	<div class="clrFlts"></div>
	<% 
		if(IActionConstants.TAB_ADMIN.equalsIgnoreCase(landingTabId)){
	%>
		<input type="hidden" name="landingTab" id="landingTab" value="lndnpgAdminTabId" />
	<%
		}
	%>
	
</div>