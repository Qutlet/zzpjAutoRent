package pl.zzpj.autorent.autorent.security;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserDto {
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;

    /**
     * Gets user First Name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets user first name
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets user last name
     * @return
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets user last name
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets user password
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user matching password
     * @return
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }

    /**
     * Set user matching password
     * @param matchingPassword
     */
    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    /**
     * Gets user email
     * @return
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets user email
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }
}
