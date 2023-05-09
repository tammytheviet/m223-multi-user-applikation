package ch.wiss.pruefung_294_295.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

// import ch.wiss.pruefung_294_295.exceptions.UserCouldNotBeUpdatedException;
import ch.wiss.pruefung_294_295.jwt.JwtUtils;
import ch.wiss.pruefung_294_295.model.*;
import ch.wiss.pruefung_294_295.repository.RoleRepository;
import ch.wiss.pruefung_294_295.repository.UserRepository;
import ch.wiss.pruefung_294_295.request.LoginRequest;
import ch.wiss.pruefung_294_295.request.SignupRequest;
import ch.wiss.pruefung_294_295.response.JwtResponse;
import ch.wiss.pruefung_294_295.response.MessageResponse;
import ch.wiss.pruefung_294_295.service.UserDetailsImpl;

import org.springframework.web.bind.annotation.GetMapping;

/**
 * Diese Klasse dient zur Authentifizierung des Benutzers
 * 
 * @class AuthController
 * @author Fabio Facundo & Tam Lai Nguyen
 * @version 1.0
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthController 
{

    //Objekt von der Klasse AuthenticationManager 'authenticationManager'
    @Autowired
    AuthenticationManager authenticationManager;

    //Objekt von der Klasse UserRepository 'userRepository'
    @Autowired
    UserRepository userRepository;

    //Objekt von der Klasse RoleRepository 'roleRepository'
    @Autowired
    RoleRepository roleRepository;

    //Objekt von der Klasse PasswordEncoder 'encoder'
    @Autowired
    PasswordEncoder encoder;

    //Objekt von der Klasse JwtUtils 'jwtUtils'
    @Autowired
    JwtUtils jwtUtils;

    /**
     * POST-Methode, um den Benutzer zu authentifizieren
     * 
     * @param loginRequest: LoginRequest
     * 
     * @return: ResponseEntity
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) 
    {
        //Authentifizierung des Benutzers
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        //Setzt die Authentifizierung
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Generiert den JWT-Token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //Holt sich die UserDetails
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //Erstellt eine Liste mit den Rollen
        List < String > roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());

        //Gibt die Antwort zurück
        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    }

    /**
     * POST-Methode, um den Benutzer zu registrieren
     * 
     * @param signUpRequest: SignupRequest
     * 
     * @return: ResponseEntity
     */
    @PostMapping("/signup")
    public ResponseEntity <?> registerUser(@Valid @RequestBody SignupRequest signUpRequest)
    {
        //Checkt, ob der Benutzername bereits existiert
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        //Checkt, ob die Email bereits existiert
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        //Erstellt einen neuen Benutzer
        User user = new User(signUpRequest.getUsername(),
                            signUpRequest.getEmail(), 
                            encoder.encode(signUpRequest.getPassword()));

        //Setzt die Rollen
        Set <String> strRoles = signUpRequest.getRole();
        Set <Role> roles = new HashSet<> ();

        //Checkt, ob die Rolle null ist
        if (strRoles == null)
        {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        }

        else
        {
            strRoles.forEach(role -> 
            {
                switch (role) 
                {
                    //Checkt, ob die Rolle 'admin' oder 'user' ist
                    case "ROLE_ADMIN":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        //Speichert den Benutzer in der Datenbank
        user.setRoles(roles);
        userRepository.save(user);

        //Gibt die Antwort zurück
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    /**
     * GET-Methode, um den Benutzer zu holen
     * 
     * @param authentication: Authentication
     * 
     * @return: ResponseEntity
     */
    @GetMapping("/account")
    public ResponseEntity<Optional<User>> getUser(Authentication authentication) 
    {
        //Holt sich den Benutzernamen
		Optional<User> user = null;
        String username = authentication.getName();

			user = userRepository.findByUsername(username);

		return ResponseEntity.ok(user);
	}

    /*@PutMapping(path = "/{id}") // UPDATE ONLY Request
    public @ResponseBody ResponseEntity<String> updateUser(@PathVariable (value = "id") int id, @RequestBody User userInfos) 
	{
		//Lädt den User aus der Datenbank
		User user = userRepository.findById(id).get();

		//Aktualisiert den User in der Datenbank
        try 
		{
            user.setUsername(userInfos.getUsername());
            user.setEmail(userInfos.getEmail());
            user.setPassword(userInfos.getPassword());
            user.setRoles(userInfos.getRoles());
			userRepository.save(user);
        } 
		catch (Exception e) 
		{
            throw new UserCouldNotBeUpdatedException(user.getUsername());
        }
		return ResponseEntity.ok("Updated");
	}*/
}