package nl.flamingostyle.voornaaminliedje.springconfig;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component("restAuthenticationEntryPoint")
public class RestAuthenticationEntryPoint implements AuthenticationEntryPoint {

	protected static final Logger logger = Logger.getLogger("service");

	@Override
	public void commence(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authException)
			throws IOException {

		logger.info("REST: Request method header: "
				+ request.getHeader("Access-Control-Request-Method"));
		logger.info("REST: Request method: " + request.getMethod());

		// response.sendError( HttpServletResponse.SC_UNAUTHORIZED,
		// "Unauthorized" );
		response.sendError(HttpServletResponse.SC_ACCEPTED, "Authorized");
		// response.sendRedirect(null);
	}
}
