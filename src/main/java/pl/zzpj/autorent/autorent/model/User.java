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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getRolesNames() {
        return rolesNames;
    }

    public void setRolesNames(List<String> rolesNames) {
        this.rolesNames = rolesNames;
    }
}