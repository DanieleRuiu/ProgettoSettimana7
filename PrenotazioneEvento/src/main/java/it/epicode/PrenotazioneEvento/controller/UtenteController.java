package it.epicode.PrenotazioneEvento.controller;

import it.epicode.PrenotazioneEvento.model.LoginRequest;
import it.epicode.PrenotazioneEvento.model.Utente;
import it.epicode.PrenotazioneEvento.model.UtenteRequest;
import it.epicode.PrenotazioneEvento.security.JwtTokenProvider;
import it.epicode.PrenotazioneEvento.service.UtenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import it.epicode.PrenotazioneEvento.model.Ruolo;

@RestController
@RequestMapping("/api/users")
public class UtenteController {

    private final UtenteService userService;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;

    @Autowired
    public UtenteController(UtenteService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/register")
    public ResponseEntity<Utente> register(@RequestBody UtenteRequest newUser) {
        Utente registeredUser = userService.registerUser(newUser);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            List<GrantedAuthority> authorities = userService.loadUserByUsername(loginRequest.getUsername()).getAuthorities();
            List<Ruolo> roles = authorities.stream()
                    .map(authority -> Ruolo.valueOf(authority.getAuthority()))
                    .collect(Collectors.toList());
            String token = jwtTokenProvider.createToken(loginRequest.getUsername(), roles);
            return ResponseEntity.ok(token);
        } catch (org.springframework.security.core.AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }


    @PutMapping("/{userId}")
    public ResponseEntity<Utente> updateUser(@PathVariable Long userId, @RequestBody Utente updatedUser) {
        Utente user = userService.updateUserDetails(userId, updatedUser);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }


}