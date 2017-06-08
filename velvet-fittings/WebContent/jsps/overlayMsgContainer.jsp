<%@page import="com.velvet.vo.MessageBean"%>
<%
String msgDispStyle = "";
String msgColorStyle = "";
String msgToDisplay = "";
MessageBean msgObj = (MessageBean)request.getAttribute("msgObject");
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

/**
Debajeet, Bug 1222, 30th July, 2012, This is a check kept for specific cases for overlays which needs to be set as static. If we need to set
any overlay message as static, we need to provide a different id in the messageContainerId request object. This is to seggregate overlapping of ids.
*/
String messageContainerId = request.getAttribute("messageContainerId")!=null ? (String)request.getAttribute("messageContainerId") : "ovrlMsgCntnrId";

%> 
<div class="msgCntnr <%=msgColorStyle%>" id="<%=messageContainerId%>" style="<%=msgDispStyle%>"><%=msgToDisplay%></div>
