package pl.zzpj.autorent.autorent.custom_requests;

import javax.validation.constraints.NotBlank;

public class TokenRefreshRequest {
    @NotBlank
    private String refreshToken;

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
}
