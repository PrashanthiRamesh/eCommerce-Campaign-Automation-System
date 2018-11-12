package concordia.comp6841.ecas.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	 	@Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/resources/**", "/h2/**");			// ignore security for these end points
	    }
	 	
	 	 @Override
	     protected void configure(HttpSecurity http) throws Exception {
	         http
				.authorizeRequests()
					.antMatchers("/register").permitAll()		
					.antMatchers("/**").hasRole("USER")			
					.and()
				.formLogin().
					failureUrl("/login-error");	

	         http.csrf().disable();
	     }
	 	 
	 	@Autowired
	    public void configureGlobal(AuthenticationManagerBuilder authenticationMgr) throws Exception {
	        authenticationMgr.inMemoryAuthentication().withUser("user").password("{noop}password")
	            .authorities("ROLE_ADMIN");
	    }
}
