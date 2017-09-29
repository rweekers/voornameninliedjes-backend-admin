@Configuration
static class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    public void init(WebSecurity web) {
        web.ignoring().antMatchers("/song/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/admin/**").authorizeRequests().anyRequest().authenticated();
    }

}
