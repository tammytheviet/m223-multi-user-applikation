package ch.wiss.pruefung_294_295.model;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * Handle Authentication Exception
 * override the commence() method. 
 * This method is triggered anytime an unauthenticated User requests a secured HTTP resource 
 * and an AuthenticationException is thrown
 */
@Component
public class AuthEntryPointJwt implements AuthenticationEntryPoint {
    private static final Logger logger = LoggerFactory.getLogger(AuthEntryPointJwt.class);
    /**
     * commence heisst "anfangen"
     */
    @Override
    public void commence(HttpServletRequest request,
        HttpServletResponse response, AuthenticationException authException)
    throws IOException, javax.servlet.ServletException {
        logger.error("Unauthorized error: {}", authException.getMessage());
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}