package pl.zzpj.autorent.autorent.repositories;

import com.google.cloud.firestore.Firestore;
import org.springframework.stereotype.Repository;
import pl.zzpj.autorent.autorent.firestore.AbstractFirestoreRepository;
import pl.zzpj.autorent.autorent.model.Comment;

@Repository
public class CommentRepository extends AbstractFirestoreRepository<Comment> {
    protected CommentRepository(Firestore firestore) { super(firestore, "comments"); }
}
