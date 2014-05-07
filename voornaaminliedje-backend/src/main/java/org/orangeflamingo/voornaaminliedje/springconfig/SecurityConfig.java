package org.orangeflamingo.voornaaminliedje.springconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.inMemoryAuthentication().withUser("admin")
				.password("5095df0e6547e2647d5bc40f1ecd9afe").roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Remco").password("ccafbc2f4c5e0d2b262ff070476678b7")
				.roles("ADMIN");
		auth.inMemoryAuthentication().withUser("Nadja").password("82adbb3e824d6e62038273b8e6ac3eb4")
		.roles("ADMIN");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers(HttpMethod.OPTIONS, "/api/admin/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/song/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/song/**").permitAll()
				.antMatchers(HttpMethod.POST, "/api/visit/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/visit/**").permitAll()
				.antMatchers(HttpMethod.GET, "/api/admin/**").hasRole("ADMIN")
				.and().httpBasic().and().csrf().disable();
	}
}