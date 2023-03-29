package ch.wiss.pruefung_294_295.controller;

import java.util.HashSet;
import java.util.List;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ch.wiss.pruefung_294_295.jwt.JwtUtils;
import ch.wiss.pruefung_294_295.model.*;
import ch.wiss.pruefung_294_295.repository.RoleRepository;
import ch.wiss.pruefung_294_295.repository.UserRepository;
import ch.wiss.pruefung_294_295.request.LoginRequest;
import ch.wiss.pruefung_294_295.request.SignupRequest;
import ch.wiss.pruefung_294_295.response.JwtResponse;
import ch.wiss.pruefung_294_295.response.MessageResponse;
import ch.wiss.pruefung_294_295.service.UserDetailsImpl;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
@Transactional
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * 
     * @param loginRequest
     * @return ResponseEntity
     * Signin with existing user
     */

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        //Authentication of the user
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                loginRequest.getPassword()));

        //Setting the Authentication inside securityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //Creation of the JSON Web Token
        String jwt = jwtUtils.generateJwtToken(authentication);

        //Extracts the data from UserDetailsImpl to Authentication
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        //Extracts the Authorities and Roles from authenticated User, and puts it inside a list which gives it as an JwtResponse back
        List < String > roles = userDetails.getAuthorities().stream()
            .map(item -> item.getAuthority())
            .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));

    }

    /**
     * 
     * @param signUpRequest
     * @return ResponseEntity
     * Creating a User
     */

    @PostMapping("/signup")
    public ResponseEntity <?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        // Checks if the username exists
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Username is already taken!"));
        }

        // Checks if the email exists
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Creating a new User
        User user = new User(signUpRequest.getUsername(),
                            signUpRequest.getEmail(), 
                            encoder.encode(signUpRequest.getPassword()));
        // Sets the userroles
        Set <String> strRoles = signUpRequest.getRole();
        Set <Role> roles = new HashSet<> ();
        // Checks, if the userrole is given
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    // Checks, if the userrole is "admin" or "user"
                    case "admin":
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
        // Saves the user inside the database
        user.setRoles(roles);
        userRepository.save(user);
        // Gives the successmessage back
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @GetMapping("account")
    public ResponseEntity<Iterable<Manga>> getAllMangas() {
		Iterable<Manga> mangas = null;

		try {
			mangas = mangaRepository.findAll();
		} catch (Exception ex) {
			throw new MangaLoadException();
		}

		return ResponseEntity.ok(mangas);
	}
}