package pl.zzpj.autorent.autorent.custom_requests;

import javax.validation.constraints.NotBlank;

public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    /**
     * Gets username
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}