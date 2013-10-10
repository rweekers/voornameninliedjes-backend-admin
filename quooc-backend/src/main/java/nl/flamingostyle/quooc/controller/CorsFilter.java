package nl.flamingostyle.quooc.controller;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.filter.OncePerRequestFilter;

public class CorsFilter extends OncePerRequestFilter {

	protected static Logger logger = Logger.getLogger("corsFilter");
	
	@Override
	protected void doFilterInternal(HttpServletRequest request,
			HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		logger.info("corsFilter..");
		

		if (request.getHeader("Access-Control-Request-Method") != null
				&& "OPTIONS".equals(request.getMethod())) {
			// CORS "pre-flight" request
			response.addHeader("Access-Control-Allow-Origin", "*");
			response.addHeader("Access-Control-Allow-Methods",
					"GET, POST, PUT, DELETE");
			response.addHeader("Access-Control-Allow-Headers", "Content-Type");
			response.addHeader("Access-Control-Max-Age", "1800");// 30 min
			
			logger.info("blablabla");
		}
		
		response.addHeader("Access-Control-Allow-Origin", "*");
		
		filterChain.doFilter(request, response);
	}
}