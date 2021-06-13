package pl.zzpj.autorent.autorent.security;

import java.util.List;

public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String email;
    private String refreshToken;
    private final List<String> roles;

    public JwtResponse(String accessToken, String email, String refreshToken, List<String> roles) {
        this.token = accessToken;
        this.email = email;
        this.refreshToken = refreshToken;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRoles() {
        return roles;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

}