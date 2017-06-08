<%@ page import="com.velvet.vo.UserBean"%>
<%@ page import="com.velvet.constants.IActionConstants"%>
<%@ page import="com.velvet.web.SessionManager"%>
<%@ page import="java.util.HashMap"%>
 
<%
	String contextFullPath = IActionConstants.DEFAULT_PROTOCOL+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
	String SESSION_EXPIRY_REDIRECT_URL = IActionConstants.DEFAULT_PROTOCOL+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/home.htm";
	
	String requestUri = request.getRequestURI();
	
	if((request.getContextPath()+"/jsps/home.jsp").equals(request.getRequestURI())){
		SessionManager.destroySession(request);
	}

	String contextPath = request.getContextPath();
	UserBean userBean = null;
	String usrInfo = "";
	String logoutDisplayStyle = "display:none";
	
	HashMap<String, Object> sessionMap = SessionManager.getSessionMap(request);
	if(sessionMap!=null){
		userBean = (UserBean)sessionMap.get(IActionConstants.USER_BEAN);
		if(userBean!=null && userBean.getUserFullName()!=null && userBean.getCurrentRoleName()!=null){
			usrInfo = "Welcome "+userBean.getUserFullName()+". You are logged in as "+userBean.getCurrentRoleName();
			logoutDisplayStyle="";
		}
	}
	
	//System.out.println("******"+request.getParameterMap());
%>
<input type="hidden" name="CONTEXT_FULL_PATH" id="CONTEXT_FULL_PATH" value="<%=contextFullPath%>"/>
<input type="hidden" name="SESSION_EXPIRY_REDIRECT_URL" id="SESSION_EXPIRY_REDIRECT_URL" value="<%=SESSION_EXPIRY_REDIRECT_URL%>"/>

<div id="trsprtOvrlId" class="trsprtOvrl" style="display:none"></div>
<div class="eriproheaderWrpr">
	<div class="eriproheader">
		<div class="eriproheaderTopPad"></div>
		<div class="eriproheaderInr">
			<div class="eriproheaderlogo">
				
			</div>
			<div class="eriproheaderAB">
				<div class="mainMenuTopPad"></div>
				<!-- eriproheader menu start-->
				<div class="eriproheadermenuWrpr">
					
				</div>
				<!-- eriproheader menu end -->

			</div>
			<div class="eriproheaderABA">
				<div class="mainMenuHeaderTPad"></div>
				<div class="mainMenuTopPad applnTitle">Velvet-Fittings</div>
				<div class="mainMenuHeaderBPad"></div>
				<!-- eriproheader menu start-->
				<div class="eriproheaderUsrInfo"><%=usrInfo%></div>
			</div>
			<div class="eriproheaderABB">
				<div class="mainMenuTopPad"></div>
			</div>
			<div class="eriproheaderBB">
				<div class="mainMenuTopPad"></div>
				<form method="post" name="logoutForm" id="logoutForm" action="logout.htm" style="display:none"></form>
				<form method="post" name="timeoutForm" id="timeoutForm" action="timeout.htm" style="display:none"></form>
				<div class="loginSectionWrpr">
					<ul class="login-section">
						<li id="logoutLink" style="<%=logoutDisplayStyle%>"><a href="javascript:void(0);" title="Logout" onclick="logoutUser()"><span class="t">Logout</span></a> </li>
					</ul>	
				</div>
			</div>
		</div>
	</div>
</div>
<!-- eriproheader End -->
<div class="color_line"></div>