package org.orangeflamingo.voornaaminliedje.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Filter to set Access-Control-Allow-Origin for calls from different server
 * 
 * @author remcow
 */
public class CorsFilter extends OncePerRequestFilter {

	/**
	 * The logger
	 */
	protected static final Logger logger = Logger.getLogger("service");

	/**
	 * The method that implements the filter
	 * 
	 * @param request
	 *            the request
	 * @param response
	 *            the response
	 * @param filterChain
	 *            the filterChain
	 * @throws ServletException
	 * @throws IOException
	 */
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		logger.info("Origin " + request.getHeader("origin"));

		logger.info("Request method header: "
				+ request.getHeader("Access-Control-Request-Method"));
		logger.info("Request method: " + request.getMethod());
		if (request.getHeader("Access-Control-Request-Method") != null
				&& "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			// response.setHeader("Access-Control-Allow-Origin", "*");
			// response.setHeader("Access-Control-Allow-Origin", "voornaaminliedje.nl");
			response.setHeader("Access-Control-Allow-Origin", "voornaaminliedje.dev");
			response.setHeader("Access-Control-Allow-Credentials", "true");
			response.setHeader("Access-Control-Allow-Methods",
					"POST, PUT, GET, DELETE, OPTIONS");
			response.setHeader("Access-Control-Max-Age", "3600");
			response.setHeader("Access-Control-Allow-Headers",
					"x-requested-with, Content-Type, Authorization, x-domain-token, _csrf_header");
		}
		response.setHeader("Access-Control-Allow-Origin", "*");
		// response.setHeader("Access-Control-Allow-Origin", "http://www.voornaaminliedje.nl");
		filterChain.doFilter(request, response);
	}
}
