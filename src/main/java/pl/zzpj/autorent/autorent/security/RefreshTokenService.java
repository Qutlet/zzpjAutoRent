package pl.zzpj.autorent.autorent.security;

import com.google.cloud.Timestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.zzpj.autorent.autorent.exceptions.GenerateTokenException;
import pl.zzpj.autorent.autorent.exceptions.RefreshTokenNotFoundException;
import pl.zzpj.autorent.autorent.exceptions.TokenRefreshException;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.model.security_model.RefreshToken;
import pl.zzpj.autorent.autorent.repositories.RefreshTokenRepository;
import pl.zzpj.autorent.autorent.repositories.UserRepository;

import java.util.Calendar;
import java.util.Optional;
import java.util.UUID;

@Service
@EnableConfigurationProperties
public class RefreshTokenService {
    @Value("${jwtRefreshExpirationMs}")
    private int refreshTokenDurationMs;

    private final RefreshTokenRepository refreshTokenRepository;

    private final UserRepository userRepository;

    private final JwtUtils jwtUtils;


    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, UserRepository userRepository, JwtUtils jwtUtils) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
        this.jwtUtils = jwtUtils;
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findBy("token", token).get(0);
    }

    /**
     * Creates refreshToken for specific user
     * @param username
     * @return
     */
    public RefreshToken createRefreshToken(String username) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUsername(username);
        refreshToken.setExpiryDate(calculateTimestampOfExpiration());
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    /**
     * Checks if token did expired
     * @param token
     * @return
     * @throws TokenRefreshException
     */
    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Timestamp.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }


    @Transactional
    public void deleteByUserId(String userId) {
        try {
            refreshTokenRepository.deleteByUserId(userId);
        } catch (RefreshTokenNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Generates token using user id
     * @param username
     * @return
     * @throws GenerateTokenException
     */
    public String generateTokenByUserId(String username) throws GenerateTokenException {
        Optional<User> userFromDatabase = userRepository.findBy("email", username).get(0);
        if (userFromDatabase.isPresent()) {
            User user = userFromDatabase.get();
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        }
        else {
            throw new GenerateTokenException();
        }
    }

    /**
     * Calculates token expiration time
     * @return
     */
    private Timestamp calculateTimestampOfExpiration() {
        long retryDate = System.currentTimeMillis();

        Timestamp now = Timestamp.now();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now.toDate());
        cal.add(Calendar.MILLISECOND, refreshTokenDurationMs);
        Timestamp later = Timestamp.of(cal.getTime());
        return later;
    }
}