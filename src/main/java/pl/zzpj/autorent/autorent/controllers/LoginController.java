package pl.zzpj.autorent.autorent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.autorent.autorent.exceptions.GenerateTokenException;
import pl.zzpj.autorent.autorent.exceptions.TokenRefreshException;
import pl.zzpj.autorent.autorent.model.security_model.RefreshToken;
import pl.zzpj.autorent.autorent.security.*;
import pl.zzpj.autorent.autorent.services.UserService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class LoginController {

    private final UserService userService;
    private final JwtUtils jwtUtils;
    private AuthenticationManager authenticationManager;
    private RefreshTokenService refreshTokenService;



    public LoginController(UserService userService, JwtUtils jwtUtils, AuthenticationManager authenticationManager, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtUtils = jwtUtils;
        this.authenticationManager = authenticationManager;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        String userId = Long.toString(userService.getUserIdByEmail(loginRequest.getUsername()));
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(loginRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(),
                refreshToken.getToken(),
                roles, userId));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        String requestRefreshToken = request.getRefreshToken();

        assert refreshTokenService.findByToken(requestRefreshToken).isPresent();
        RefreshToken token = refreshTokenService.findByToken(requestRefreshToken).get();
        try {
            token = refreshTokenService.verifyExpiration(token);
            String username = token.getUsername();
            String tokenString = refreshTokenService.generateTokenByUserId(username);
            return ResponseEntity.ok(new TokenRefreshResponse(tokenString, requestRefreshToken));
        } catch (TokenRefreshException | GenerateTokenException tokenRefreshException) {
            tokenRefreshException.printStackTrace();
        }
        return new ResponseEntity<>("Refresh token error", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
