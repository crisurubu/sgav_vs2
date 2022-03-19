package infotec.sgva.config;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsUtils;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import infotec.sgva.services.login.JwtService;
import infotec.sgva.services.login.SecurityUserDetailService;



@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	
	
	@Autowired
	private SecurityUserDetailService userDetailsService;
	@Autowired
	private JwtService jwtService;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		PasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	
	@Bean
	public JwtTokenFilter jwtTokenFilter() {
		return new JwtTokenFilter(jwtService, userDetailsService);
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder());
		
		
	}
	
	
	
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().requestMatchers(CorsUtils::isPreFlightRequest).permitAll()
							 .antMatchers(HttpMethod.POST,"/usuarios").permitAll()
							 .antMatchers(HttpMethod.POST,"/usuarios/autenticar").permitAll()
							 .antMatchers(HttpMethod.POST,"/funcionarios").permitAll()
							 .antMatchers(HttpMethod.GET,"/funcionarios").permitAll()
							 .antMatchers(HttpMethod.GET,"/funcionarios/{id}").permitAll()
							 .antMatchers(HttpMethod.PUT,"/funcionarios/{id}").permitAll()
							 .antMatchers(HttpMethod.PUT,"/funcionarios/{id}/demitir").permitAll()
							 .antMatchers(HttpMethod.DELETE,"/funcionarios/{id}").permitAll()
							 .antMatchers(HttpMethod.GET,"/funcoes").permitAll()
							 .antMatchers(HttpMethod.GET,"/funcoes/{id}").permitAll()
							 .antMatchers(HttpMethod.POST,"/veiculos").permitAll()
							 .anyRequest()
							 .authenticated()
							 .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
							 .and()
							 .addFilterBefore(jwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
		
		
							
	}
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter(){
		
		List<String> all = Arrays.asList("*");
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedMethods(all);
		config.setAllowedOriginPatterns(all);
		config.setAllowedHeaders(all);
		config.setAllowCredentials(true);;
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		
		CorsFilter corFilter = new CorsFilter(source);
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<CorsFilter>(corFilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filter;
		
	}
	
	

}
