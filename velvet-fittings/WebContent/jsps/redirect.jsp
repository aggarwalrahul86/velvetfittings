<%@page contentType="text/html" pageEncoding="UTF-8"%>
<% 
String contextPath = request.getContextPath(); 

String rdrctnUrl = contextPath+"/home/welcome.htm";
if("1".equals(request.getParameter("logout"))){
	rdrctnUrl = rdrctnUrl+"?logout=1";
}else if("1".equals(request.getParameter("ssntmt"))){
	rdrctnUrl = rdrctnUrl+"?ssntmt=1";
}
response.sendRedirect(rdrctnUrl); 
%>