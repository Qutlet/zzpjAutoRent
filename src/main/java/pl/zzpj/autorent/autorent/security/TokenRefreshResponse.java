package pl.zzpj.autorent.autorent.security;

public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    /**
     * Token refresh response constructor
     * @param accessToken
     * @param refreshToken
     */
    public TokenRefreshResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    /**
     * Gets accessToken
     * @return
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * Sets accessToken
     * @param accessToken
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * Gets refreshToken
     * @return
     */
    public String getRefreshToken() {
        return refreshToken;
    }

    /**
     * Sets refreshToken
     * @param refreshToken
     */
    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    /**
     * Gets token type
     * @return
     */
    public String getTokenType() {
        return tokenType;
    }

    /**
     * Sets Token type
     * @param tokenType
     */
    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}