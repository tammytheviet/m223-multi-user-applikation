package ch.wiss.pruefung_294_295.jwt;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import ch.wiss.pruefung_294_295.service.UserDetailsImpl;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
  * Diese Klasse hat 3 Hauptfunktionen:
    * generateJwtToken: Erstellt ein JWT Token aus einem Auth-Objekt
    * getUserNameFromJwtToken: Holt den Benutzernamen aus dem JWT
    * validateJwtToken: Validiert ein JWT mit einem Secret
  *
  * @class JwtUtils
  * @author Fabio Facundo & Tam Lai Nguyen
  * @version 1.0
  *
  */
@Component
public class JwtUtils 
{

    //Logger
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    //Secret aus der application.properties-Datei
    @Value("${mangalib.app.jwtSecret}")
    private String jwtSecret;

    //Expiration in Millisekunden aus der application.properties-Datei
    @Value("${mangalib.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Erstellt ein JWT-Token aus einem Auth-Objekt
     * 
     * @param authentication
     * 
     * @return String
     */
    public String generateJwtToken(Authentication authentication) 
    {

        //Holt den Benutzernamen aus dem Auth-Objekt
        UserDetailsImpl userPrincipal = (UserDetailsImpl)

        //Erstellt das JWT-Token
        authentication.getPrincipal();

        //Gibt das JWT-Token zurück
        return Jwts.builder()
            .setSubject((userPrincipal.getUsername()))
            .setIssuedAt(new Date())
            .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, jwtSecret)
            .compact();
    }

    /**
     * Holt den Benutzernamen aus dem JWT
     * 
     * @param token
     * 
     * @return String
     */
    public String getUserNameFromJwtToken(String token) 
    {

        //Gibt den Benutzernamen zurück
        return Jwts.parser().setSigningKey(jwtSecret)
            .parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Validiert ein JWT mit einem Secret
     * 
     * @param authToken
     * 
     * @return boolean
     */
    public boolean validateJwtToken(String authToken) 
    {
        //Versucht das JWT-Token zu validieren
        try 
        {
            Jwts.parser().setSigningKey(jwtSecret)
                .parseClaimsJws(authToken);
            return true;
        }
        //Nicht validierbares JWT-Token
        catch (SignatureException e)
        {
            logger.error("Invalid JWT signature: {}", e.getMessage());
        }
        //Falsches JWT-Token
        catch (MalformedJwtException e)
        {
            logger.error("Invalid JWT token: {}", e.getMessage());
        }
        //Abgelaufenes JWT-Token
        catch (ExpiredJwtException e)
        {
            logger.error("JWT token is expired: {}", e.getMessage());
        }
        //Nicht unterstütztes JWT-Token
        catch (UnsupportedJwtException e)
        {
            logger.error("JWT token is unsupported: {}",
                e.getMessage());
        }
        //Leerer JWT-Token
        catch (IllegalArgumentException e)
        {
            logger.error("JWT claims string is empty: {}",
                e.getMessage());
        }
        return false;
    }
    
}