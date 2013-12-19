package nl.flamingostyle.voornaaminliedje.controller;

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
    protected static final Logger logger = Logger.getLogger("corsFilter");

    /**
     * The method that implements the filter
     *
     * @param request the request
     * @param response the response
     * @param filterChain the filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request,
            HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        if (request.getHeader("Access-Control-Request-Method") != null
                && "OPTIONS".equals(request.getMethod())) {
            // CORS "pre-flight" request
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Allow-Methods",
                    "GET, POST, PUT, DELETE");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type");
            response.addHeader("Access-Control-Max-Age", "1800");// 30 min

        }

        response.addHeader("Access-Control-Allow-Origin", "*");

        filterChain.doFilter(request, response);
    }
}
