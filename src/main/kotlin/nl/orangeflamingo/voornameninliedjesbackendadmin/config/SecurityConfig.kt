package nl.orangeflamingo.voornameninliedjesbackendadmin.config

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder





@Configuration
@EnableWebSecurity
class SecurityConfig: WebSecurityConfigurerAdapter() {

    @Autowired
    private lateinit var authenticationEntryPoint: MyBasicAuthPoint

    @Value("\${orangeflamingo.admin.user1}")
    private lateinit var user1: String

    @Value("\${orangeflamingo.admin.password1}")
    private lateinit var password1: String

    @Value("\${orangeflamingo.admin.user2}")
    private lateinit var user2: String

    @Value("\${orangeflamingo.admin.password2}")
    private lateinit var password2: String

    @Autowired
    fun configureGlobal(auth: AuthenticationManagerBuilder) {
        auth.inMemoryAuthentication()
                .withUser(user1).password(passwordEncoder().encode(password1))
                .roles("ADMIN", "OWNER")
                .and()
                .withUser(user2).password(passwordEncoder().encode(password2))
                .roles("ADMIN")
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                // specify that any api url added later will be allowed and all admin is only usable by ADMIN or OWNER
                .antMatchers("/api/**").permitAll()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/owner/**").hasRole("OWNER")
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .authenticationEntryPoint(authenticationEntryPoint)
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return BCryptPasswordEncoder()
    }
}