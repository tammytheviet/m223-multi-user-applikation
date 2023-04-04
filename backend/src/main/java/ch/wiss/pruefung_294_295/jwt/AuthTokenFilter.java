package ch.wiss.pruefung_294_295.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import ch.wiss.pruefung_294_295.service.UserDetailsServiceImpl;

/**
 * Diese Klasse wird verwendet, um die Anmeldung und Registrierung zu ermöglichen.
 * 
 * @class AuthTokenFilter
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
public class AuthTokenFilter extends OncePerRequestFilter 
{

    //Objekt von der Klasse Logger 'logger'
    @Autowired
    private JwtUtils jwtUtils;

    //Objekt von der Klasse UserDetailsServiceImpl 'userDetailsService'
    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    //Objekt von der Klasse Logger 'logger'
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    /**
     * Methode, die die Anmeldung und Registrierung ermöglicht
     * 
     * @param request: HttpServletRequest
     * @param response: HttpServletResponse
     * @param filterChain: FilterChain
     * 
     * @throws ServletException
     * @throws IOException
     * 
     * @return void
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //Ausgabe der Fehlermeldung
        try 
        {
            String jwt = parseJwt(request);

            if (jwt != null && jwtUtils.validateJwtToken(jwt)) 
            {
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } 
        catch (Exception e) 
        {
            logger.error("Cannot set user authentication: {}", e);
        }

        filterChain.doFilter(request, response);
    }

    /**
     * Methode, die die Anmeldung und Registrierung ermöglicht
     * 
     * @param request: HttpServletRequest
     * 
     * @return String
     */
    private String parseJwt(HttpServletRequest request) 
    {
        String headerAuth = request.getHeader("Authorization");

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) 
        {
            return headerAuth.substring(7, headerAuth.length());
        }

        return null;
    }
}