package pl.zzpj.autorent.autorent.repositories;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.exceptions.RefreshTokenNotFoundException;
import pl.zzpj.autorent.autorent.firestore.AbstractFirestoreRepository;
import pl.zzpj.autorent.autorent.model.security_model.RefreshToken;

import java.util.Optional;

@Repository
public class RefreshTokenRepository extends AbstractFirestoreRepository<RefreshToken> {
    protected RefreshTokenRepository(Firestore firestore) {
        super(firestore, "refresh_tokens");
    }

    public void deleteByUserId(String id) throws RefreshTokenNotFoundException {
        Optional<RefreshToken> tokenFromDatabase = this.findBy("userId", id).get(0);
        if(tokenFromDatabase.isPresent()){
            RefreshToken token = tokenFromDatabase.get();
            this.delete(token);
        }
        else {
            throw new RefreshTokenNotFoundException();
        }
    }
}
