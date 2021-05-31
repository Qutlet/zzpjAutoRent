package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.repositories.CommentRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Comment getComment(String id) {
        return commentRepository.get(id).orElseThrow();
    }

    public void addComment(Comment comment){
        comment.setId(UUID.randomUUID().toString());
        commentRepository.save(comment);
    }

    public void deleteComment(String id) {
        Comment comment = getComment(id);
        commentRepository.deleteById(id);
    }

    public List<Comment> getAllComments() throws IOException, InterruptedException {
        return commentRepository.retrieveAll();
    }
}
