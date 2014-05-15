package org.orangeflamingo.voornaaminliedje.springconfig;

import org.apache.log4j.Logger;
import org.orangeflamingo.voornaaminliedje.controller.CustomBasicAuthenticationEntryPoint;
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

	protected static Logger logger = Logger.getLogger("service");

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("admin")
				.password("5095df0e6547e2647d5bc40f1ecd9afe").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Remco")
				.password("ccafbc2f4c5e0d2b262ff070476678b7").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Nadja")
				.password("82adbb3e824d6e62038273b8e6ac3eb4").roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		logger.info("Configuring securityconfig...");

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
		logger.info("bladiebladiebla.");
		basicAuthenticationEntryPoint.setRealmName("Basic WF Realm");
		return basicAuthenticationEntryPoint;
	}
}