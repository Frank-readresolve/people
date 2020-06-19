package fr.formation.people.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.web.session.SessionManagementFilter;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {
	
    @Value("${people.allowedOrigin}")
    private String allowedOrigin;

    /**
     * Configures the HTTP security for this application.
     * <p>
     * Defines this application as stateless (no HTTP session), and disables
     * HTTP basic auth and CSRF at this point.
     *
     * @param the HttpSecurity to configure
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
	// Disable CSRF, no need with JWT if not cookie-based.
	// Anonymous is enabled by default.
	http.httpBasic().disable().csrf().disable().sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
		// Add the CORS filter to the security configuration
		.addFilterBefore(corsFilter(), SessionManagementFilter.class)
		// "/api/users/**" for anyone even anonymous, only POST method
		// in order to create a user
		.authorizeRequests().antMatchers(HttpMethod.POST, "/api/users").permitAll()
		// Return 200 OK for any OPTIONS (preflight) requests instead of 401
		.antMatchers(HttpMethod.OPTIONS).permitAll()
		/*
		 * "/api/persons/**", "/api/addresses/**" for authenticated
		 * (not anonymous) users only
		 */
		.antMatchers("/api/persons/**", "/api/addresses/**").authenticated(); // Could be .anyRequest().authenticated();
    }
    
    @Bean
    CorsFilter corsFilter() {
    	return new CorsFilter(allowedOrigin);
    }
    
}
