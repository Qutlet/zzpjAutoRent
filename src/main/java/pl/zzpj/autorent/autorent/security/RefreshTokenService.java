package pl.zzpj.autorent.autorent.security;

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

import java.time.Instant;
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

    public RefreshToken createRefreshToken(Long userId) {
        RefreshToken refreshToken = new RefreshToken();

        refreshToken.setUserId(Long.toString(userId));
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTokenDurationMs));
        refreshToken.setToken(UUID.randomUUID().toString());

        refreshTokenRepository.save(refreshToken);
        return refreshToken;
    }

    public RefreshToken verifyExpiration(RefreshToken token) throws TokenRefreshException {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new TokenRefreshException(token.getToken(), "Refresh token was expired. Please make a new signin request");
        }

        return token;
    }

    @Transactional
    public void deleteByUserId(Long userId) {
        try {
            refreshTokenRepository.deleteByUserId(Long.toString(userId));
        } catch (RefreshTokenNotFoundException e) {
            e.printStackTrace();
        }
    }

    public String generateTokenByUserId(String userId) throws GenerateTokenException {
        Optional<User> userFromDatabase = userRepository.findBy("id", userId).get(0);
        if (userFromDatabase.isPresent()) {
            User user = userFromDatabase.get();
            CustomUserDetails userDetails = new CustomUserDetails(user);
            return jwtUtils.generateTokenFromUsername(userDetails.getUsername());
        }
        else {
            throw new GenerateTokenException();
        }
    }
}