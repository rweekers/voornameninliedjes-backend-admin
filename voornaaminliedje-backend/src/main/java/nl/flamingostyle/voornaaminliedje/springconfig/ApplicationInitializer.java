package nl.flamingostyle.voornaaminliedje.springconfig;

import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import nl.flamingostyle.voornaaminliedje.controller.CorsFilter;

import org.apache.log4j.Logger;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

public class ApplicationInitializer implements WebApplicationInitializer {

	protected static Logger logger = Logger.getLogger("corsFilter");

	@Override
	public void onStartup(ServletContext container) throws ServletException {

		// Create the 'root' Spring application context
		AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
		rootContext.register(WebConfig.class, SecurityConfig.class);

		// Manage the lifecycle of the root application context
		container.addListener(new ContextLoaderListener(rootContext));

		// Create the dispatcher servlet's Spring application context
		AnnotationConfigWebApplicationContext dispatcherContext = new AnnotationConfigWebApplicationContext();
		dispatcherContext.register(WebConfig.class);

		// Register and map the dispatcher servlet
		ServletRegistration.Dynamic dispatcher = container.addServlet(
				"dispatcher", new DispatcherServlet(dispatcherContext));
		dispatcher.setLoadOnStartup(1);
		dispatcher.addMapping("/");

		logger.info("WebapplicationInitializer started...");

		// Register Spring security filter
		FilterRegistration.Dynamic corsFilter = container.addFilter(
				 "corsFilter", CorsFilter.class);
		/*
		FilterRegistration.Dynamic corsFilter = container.addFilter(
				"corsFilter", CORSFilter.class);
		corsFilter.setInitParameter("cors.supportedHeaders", "Content-Type,Accept,Origin");*/
		corsFilter.addMappingForUrlPatterns(null, false, "/*");

		configureSpringSecurity(container, rootContext);

	}

	private void configureSpringSecurity(ServletContext servletContext,
			WebApplicationContext rootContext) {
		FilterRegistration.Dynamic springSecurity = servletContext.addFilter(
				"springSecurityFilterChain", new DelegatingFilterProxy(
						"springSecurityFilterChain", rootContext));
		springSecurity.addMappingForUrlPatterns(null, true, "/*");
	}
}