package pl.zzpj.autorent.autorent.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.zzpj.autorent.autorent.firestore.DocumentId;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @DocumentId
    private String id;
    private String firstName;
    private String lastName;
    private String password;
    private String matchingPassword;
    private String email;
    private List<String> rolesNames;

    /**
     * Gets user id
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Sets user id
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets user first name
     * @return
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     *
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
     * Sets user last name field
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Sets user password
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets user matchnig password
     * @return
     */
    public String getMatchingPassword() {
        return matchingPassword;
    }

    /**
     * Sets matching password field
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
     * Sets email of user
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets role names for user
     * @return
     */
    public List<String> getRolesNames() {
        return rolesNames;
    }

    /**
     * Sets role names for user
     * @param rolesNames
     */
    public void setRolesNames(List<String> rolesNames) {
        this.rolesNames = rolesNames;
    }
}