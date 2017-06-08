package com.velvet.web;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.velvet.constants.IApplicationConstants;


public class SessionManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Timestamp accessTimeStamp = null;

	private HashMap<String, Object> sessionMap = null;

	/** The no-arg construcutor */

	public SessionManager(HttpServletRequest request) {
		sessionMap = new HashMap<String, Object>();
		Timestamp accessTimeStamp = new Timestamp(System.currentTimeMillis());
		this.accessTimeStamp = accessTimeStamp;
		
		if(request.getSession(false)!=null){
			request.getSession().invalidate();
		}
		
		request.getSession(true).setAttribute(IApplicationConstants.HTTP_SESSION_MAP_KEY, sessionMap);
	}
	
	public static HashMap<String, Object> getSessionMap(HttpServletRequest request) {
		HashMap<String, Object> sessionMap = null;
		if(request.getSession(false)!=null){
			sessionMap = (HashMap<String, Object>)(request.getSession(false).getAttribute(IApplicationConstants.HTTP_SESSION_MAP_KEY));
		}
		
		return sessionMap;
	}



	public static void destroySession(HttpServletRequest request) {
		
		if(request.getSession(false)!=null){
			request.getSession(false).setAttribute(IApplicationConstants.HTTP_SESSION_MAP_KEY, null);
			request.getSession().invalidate();
		}
	}

	public HttpSession getHttpSession(HttpServletRequest request) {
		return request.getSession(false);
	}
	
	public Timestamp getAccessTimeStamp() {
		return accessTimeStamp;
	}

	public void setAccessTimeStamp(Timestamp accessTimeStamp) {
		this.accessTimeStamp = accessTimeStamp;
	}

	
}