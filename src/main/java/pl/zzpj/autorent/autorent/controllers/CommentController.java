package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.services.CarService;
import pl.zzpj.autorent.autorent.services.CommentService;

import java.io.IOException;
import java.util.List;


import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private CarService carService;

    /**
     * Gets all comments
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping(path = "/comments", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllComments() throws IOException, InterruptedException {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    /**
     * Gets specific comment by its id
     * @param id
     * @return
     */
    @GetMapping(path = "/comments/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCommentById(@PathVariable String id) {
        final Comment comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    /**
     * Gets comment for specific car by its id
     * @param carId
     * @return
     * @throws IOException
     * @throws InterruptedException
     */
    @GetMapping(path="/comments/car/{carId}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCommentsByCarId(@PathVariable String carId) throws IOException, InterruptedException {
        final List<Comment> comments = commentService.getAllCommentsByCarID(carId);
        return ResponseEntity.ok(comments);
    }

    /**
     * Adds comment
     * @param comment
     * @return
     */
    @PostMapping(path = "/comments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        carService.addComment(comment.getCarID(), comment);
        return ResponseEntity.ok().build();
    }

    /**
     * Deletes comment by its id
     * @param id
     * @return
     */
    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable String id) {
        Comment comment = commentService.getComment(id);
        carService.deleteComment(comment.getCarID(), comment);
        commentService.deleteComment(id);
        return ResponseEntity.accepted().build();
    }

}