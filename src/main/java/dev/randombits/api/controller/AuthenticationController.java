package dev.randombits.api.controller;

import dev.randombits.api.model.JwtRequest;
import dev.randombits.api.model.JwtResponse;
import dev.randombits.api.service.JwtUserDetailsService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@CrossOrigin
public class AuthenticationController {
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    @Value("${jwt.secret}")
    private String secret;

//    @Autowired
//    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;


    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

//        final UserDetails userDetails = jwtUserDetailsService
//                .loadUserByUsername(authenticationRequest.getUsername());


        String token = this.generateToken(authenticationRequest.getUsername());

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String username, String password) throws Exception {
        try {

//            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    private String generateToken(String username) throws Exception {
        try {
            return Jwts.builder().setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
                    .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                    .signWith(SignatureAlgorithm.HS512, secret).compact();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            throw new Exception("Unable to generate token", e);
        }
    }
}
