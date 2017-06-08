<%
	String contextPath = request.getContextPath();
%>
<div class="bdCntntABAC" id="bdCntntABACId">
		<div class="bdCntntABACInr" id="bdCntntABACInrId">
			<div class="bdCntntABACTextCntnr">
				<div class="bdCntntABACTextWrpr">
					<jsp:include page="messageContainer.jsp"></jsp:include>
					<div class="loginBdCntnr" id="loginBdCntnrId">
						<div class="loginBdCntnrInr" id="loginBdCntnrInrId">
							<div id="loginCredentialsCntnrId">
								<form name="loginForm" method="POST" id="loginForm"
									action="<%=contextPath+"/home/login.htm" %>" >
									<input type="hidden" name="isFromLogin" id="isFromLogin"
										value="Y" />
									<input type="hidden" name="firstLoginFlag" id="firstLoginFlag"
										value="<%=(String)request.getAttribute("firstLoginFlag")%>" />
									<input type="hidden" name="userNameHdn" id="userNameHdn"
										value="<%=(String)request.getAttribute("userId")%>" />	
										
									<div class="loginLbl">
										<label>User Name</label><span class="rqrdmrk">*</span>
										<br><div class="loginLblSmall"></div>
									</div>
									<div class="inrPgBdElmt">
										<input type="text" class="loginTextBox" name="username" validatorLabel="User Name"
											id="username" title="User Name">
									</div>
									<div class="clrFlts"></div>
									<div class="loginLbl">
										<label>Password</label><span class="rqrdmrk">*</span>
										<br><div class="loginLblSmall"></div>
									</div>
									<div class="inrPgBdElmt">
										<input type="password" class="loginPasswordBox" validatorLabel="Password"
											name="password" id="password" title="Password" onkeydown="loginOnEnterPress(event);">
									</div>
									<div class="clrFlts"></div>
									<div class="loginBtnCntnrWrprOutter">
										<div id="loginBtnCntnrWrprDiv" class="loginBtnCntnrWrpr" onclick="submitLoginForm()">
											<div class="loginBtnCntnr" id="loginBtnCntnrId">
												<div class="btnBkGrndGrdnt loginBtnCntnrInr"
													id="loginBtnCntnrInrId">
													<div class="loginBtnCntnrText">Login</div>
												</div>

											</div>
										</div>
									</div>
								</form>
								</div>
								
						</div>

					</div>
						
				</div>

				<div class="bdHPd"></div>
			</div>
		</div>
	</div>
