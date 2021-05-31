package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.autorent.autorent.exceptions.UserAlreadyExistException;
import pl.zzpj.autorent.autorent.model.Offer;
import pl.zzpj.autorent.autorent.security.UserDto;
import pl.zzpj.autorent.autorent.services.OfferService;
import pl.zzpj.autorent.autorent.services.UserService;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class RegiterController {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/register", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity registerUser(@RequestBody UserDto userDto) {
        try {
            userService.registerNewUserAccount(userDto);
        } catch (UserAlreadyExistException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(userDto);
    }
}
