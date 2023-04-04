package ch.wiss.pruefung_294_295.jwt;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Diese Klasse wird verwendet, um die Anmeldung und Registrierung zu ermöglichen.
 * 
 * @class AuthEntryPointJwt
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 * 
 * @param request: HttpServletRequest
 * @param response: HttpServletResponse
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint 
{
    //Objekt von der Klasse Logger 'logger'
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);

    //Methode, die die Anmeldung und Registrierung ermöglicht
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, javax.servlet.ServletException 
    {
        //Ausgabe der Fehlermeldung
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}