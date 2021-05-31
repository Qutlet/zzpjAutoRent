package pl.zzpj.autorent.autorent.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.zzpj.autorent.autorent.model.Car;
import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.repositories.CarRepository;
import pl.zzpj.autorent.autorent.repositories.CommentRepository;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CommentService {

    private CommentRepository commentRepository;
    private CarRepository carRepository;

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
        commentRepository.deleteById(id);
    }

    public List<Comment> getAllComments() throws IOException, InterruptedException {
        return commentRepository.retrieveAll();
    }

    public List<Comment> getAllCommentsByCarID(String carId) throws IOException, InterruptedException {
        List<Comment> comments = commentRepository.retrieveAll()
                .stream()
                .filter( comment -> comment.getCarID().equals(carId))
                .collect(Collectors.toList());
        return comments;
    }
}
