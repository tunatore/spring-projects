package springmvc.java.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		
		/*
		 * in memory authentication definition by setting username and password
		 * 
		 */
		
			auth.inMemoryAuthentication()
					.withUser("user")
						.password("password")
							.roles("USER");
			
			auth.inMemoryAuthentication()
					.withUser("admin")
						.password("password")
							.roles("ADMIN");
			
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
			web.ignoring()
					.antMatchers("/resources/**");	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
			http.csrf().disable()
					.authorizeRequests()
							.antMatchers("/admin**").hasRole("ADMIN")
							.antMatchers("/**").hasAnyRole("USER","ADMIN")
							.anyRequest().authenticated()
					.and()
						.formLogin()
					.and()
						.exceptionHandling().accessDeniedPage("/accessDenied");
						
	}

}
