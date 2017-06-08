<%@page import="com.velvet.vo.MessageBean"%>
<%
String msgDispStyle = "";
String msgColorStyle = "";
String msgToDisplay = "";
MessageBean msgObj = (MessageBean)request.getAttribute("msgObject");

if("1".equals(request.getParameter("ssntmt"))){
	msgObj = new MessageBean(MessageBean.ERROR,"Your session has timed out. Please login again.");
}else if("1".equals(request.getParameter("logout"))){
	msgObj = new MessageBean(MessageBean.CONFIRMATION,"You have succcessfully logged out.");
}

if(msgObj != null){
	if(msgObj.getMessage()==null) {
		msgDispStyle = "display:none";
	}else{
		msgDispStyle = "display:block";
		if(msgObj.getSeverity() == 1){
			msgColorStyle = "errorMsg";
			msgToDisplay = (String)msgObj.getMessage();
		}else if(msgObj.getSeverity() == 3){
			msgColorStyle = "successMsg";
			msgToDisplay = (String)msgObj.getMessage();
		}
	}
}else{
	msgDispStyle = "display:none";
}
%> 
<div class="msgCntnr <%=msgColorStyle%>" id="msgCntnrId" style="<%=msgDispStyle%>"><%=msgToDisplay%></div>
