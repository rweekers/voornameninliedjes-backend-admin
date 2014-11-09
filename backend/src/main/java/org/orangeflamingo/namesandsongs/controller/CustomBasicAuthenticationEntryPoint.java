package org.orangeflamingo.namesandsongs.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

/**
 * Does not add header that requires a username and password via a popup
 * 
 */
public class CustomBasicAuthenticationEntryPoint extends
		BasicAuthenticationEntryPoint {

	protected static Logger logger = Logger.getLogger(CustomBasicAuthenticationEntryPoint.class);

	public CustomBasicAuthenticationEntryPoint(String realmName) {
		logger.debug("Creating customBasicAuthenticationEntryPoint");
		setRealmName(realmName);
	}

	@Override
	public void commence(final HttpServletRequest request,
			final HttpServletResponse response,
			final AuthenticationException authException) throws IOException,
			ServletException {
		logger.debug("CustomBasicAuthentication ontvangt request: "
				+ request.getRequestURL() + " met methode: "
				+ request.getMethod());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,
				authException.getMessage());
	}

}
