package org.orangeflamingo.namesandsongs.springconfig;

import org.apache.log4j.Logger;
import org.orangeflamingo.namesandsongs.controller.CustomBasicAuthenticationEntryPoint;
import org.orangeflamingo.namesandsongs.service.PropertiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final Logger LOGGER = Logger.getLogger(SecurityConfig.class);
	
	@Autowired
	private PropertiesService propertiesService;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		
		auth.inMemoryAuthentication().withUser(propertiesService.get("userAdmin"))
				.password(propertiesService.get("passwordAdmin")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser(propertiesService.get("userRemco"))
				.password(propertiesService.get("passwordRemco")).roles("ADMIN");
		auth.inMemoryAuthentication().withUser(propertiesService.get("userNadja"))
				.password(propertiesService.get("passwordNadja")).roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		LOGGER.debug("Configuring securityconfig...");

		// TODO Check if http.exceptionHandling weg kan
		// (https://jira.spring.io/browse/SEC-2198)

		// http.exceptionHandling().authenticationEntryPoint(entryPoint()).and()
		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/api/admin/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/song/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/song/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/visit/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/visit/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
				.and().httpBasic().and().csrf().disable().httpBasic()
				.authenticationEntryPoint(entryPoint());
	}

	@Bean
	public BasicAuthenticationEntryPoint entryPoint() {
		BasicAuthenticationEntryPoint basicAuthenticationEntryPoint = new CustomBasicAuthenticationEntryPoint(
				"RealmName");
		LOGGER.debug("Creating BasicAuthenticationEntryPoint bean");
		basicAuthenticationEntryPoint.setRealmName("Basic WF Realm");
		return basicAuthenticationEntryPoint;
	}
}