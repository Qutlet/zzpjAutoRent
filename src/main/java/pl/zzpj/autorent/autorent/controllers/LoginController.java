package pl.zzpj.autorent.autorent.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.zzpj.autorent.autorent.services.UserService;

@RestController
public class LoginController {

    @Autowired
    private UserService userService;
}
