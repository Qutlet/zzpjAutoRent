package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import pl.zzpj.autorent.autorent.model.Comment;
import pl.zzpj.autorent.autorent.services.CommentService;

import java.io.IOException;

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

    // TODO: end
    @PostMapping(path = "/comments", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addComment(@RequestBody Comment comment) {
        return ResponseEntity.ok(comment);
    }
    // TODO: end
    @DeleteMapping(path = "/comments/{id}")
    public ResponseEntity deleteComment(@PathVariable String id) {
        return ResponseEntity.accepted().build();
    }

}