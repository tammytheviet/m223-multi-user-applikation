package ch.wiss.pruefung_294_295.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import ch.wiss.pruefung_294_295.jwt.AuthTokenFilter;
import ch.wiss.pruefung_294_295.service.UserDetailsServiceImpl;

/**
 * Diese Klasse dient zur Konfiguration von der WebSecurity.
 * 
 * @class WebSecurityConfig
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig 
{

    //Objekt von der Klasse UserDetailsServiceImpl 'userDetailsService'
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Objekt von der Klasse AuthenticationEntryPoint 'unauthorizedHandler'
    @Autowired
    private AuthenticationEntryPoint unauthorizedHandler;

    //Array, dass die Pfade für die öffentlichen Endpunkte hat
    private final static String[] EVERYONE = 
    {
        "/api/auth/**",
        "/manga",
        "/chapter"
    };

    //Array, dass die Pfade für die geschützten Endpunkte hat
    private final static String[] SECURE = 
    {
        "/question"
    };

    //Array, dass die erweiterten Rollen hat
    private final static String[] ROLES = 
    {
        "ADMIN"
    };

    @Bean
    public AuthTokenFilter authenticationJwtTokenFilter() 
    {
        return new AuthTokenFilter();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() 
    {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception 
    {
        return authConfig.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() 
    {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {
        http.cors().and().csrf().disable()
            .exceptionHandling()
            .authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement()
            .sessionCreationPolicy(
                SessionCreationPolicy.STATELESS)
            .and()
            .authorizeHttpRequests((authz) ->
                authz.antMatchers(EVERYONE).permitAll()
                .antMatchers(SECURE).hasAnyRole(ROLES)
                .anyRequest().authenticated()
            );
        http.authenticationProvider(authenticationProvider());
        http.addFilterBefore(authenticationJwtTokenFilter(),
            UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

}