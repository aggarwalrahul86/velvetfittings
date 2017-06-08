<%@ page import="com.velvet.constants.IActionConstants"%>
<%
	String contextFullPath = IActionConstants.DEFAULT_PROTOCOL+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
%>
	<input type="hidden" name="versionDetailsUrl" id="versionDetailsUrl" value="<%=contextFullPath%>/version" />
	<div class="color_line"></div>
	<div class="footerWrpr">
		<div class="footer">
			<div class="footerInr">
				<div class="footerInrA">
					<font class="footerInrC">*Application is best viewed in Firefox and Chrome.</font>
				</div>
				<div class="footerInrB">
					Velvet-Fittings Inventory Management System <br/>Copyright &copy; 2013
				</div>
			</div>
		</div>
	</div>