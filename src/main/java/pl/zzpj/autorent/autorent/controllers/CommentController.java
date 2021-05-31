package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.services.CommentService;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping(path = "/comments", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllComments() throws IOException, InterruptedException {
        return ResponseEntity.ok(commentService.getAllComments());
    }

    @GetMapping(path = "/comments/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCommentById(@PathVariable String id) {
        final Comment comment = commentService.getComment(id);
        return ResponseEntity.ok(comment);
    }

    @GetMapping(path="/comments/{carID}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getCommentsByClientID(@PathVariable String carID) throws IOException, InterruptedException {
        final List<Comment> comments = commentService.getAllCommentsByCarID(carID);
        return ResponseEntity.ok(comments);
    }

    @PostMapping(path = "/comments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addComment(@RequestBody Comment comment) {
        commentService.addComment(comment);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable String id) {
        commentService.deleteComment(id);
        return ResponseEntity.accepted().build();
    }

}