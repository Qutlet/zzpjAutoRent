package pl.zzpj.autorent.autorent.model.security_model;

import com.google.cloud.Timestamp;
import com.google.cloud.firestore.annotation.ServerTimestamp;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String username;
    private String token;
    @ServerTimestamp
    private Timestamp expiryDate;

    /**
     * Gets token id
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * Sets token id
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets username from token
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets RefreshToken username field
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets token
     * @return
     */
    public String getToken() {
        return token;
    }

    /**
     * Sets token
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * Gets expiration date for token
     * @return
     */
    public Timestamp getExpiryDate() {
        return expiryDate;
    }

    /**
     * Sets expiration date
     * @param expiryDate
     */
    public void setExpiryDate(Timestamp expiryDate) {
        this.expiryDate = expiryDate;
    }
}