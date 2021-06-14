package pl.zzpj.autorent.autorent.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.zzpj.autorent.autorent.custom_requests.RoleChangeRequest;
import pl.zzpj.autorent.autorent.custom_responses.RoleChangeResponse;
import pl.zzpj.autorent.autorent.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    final
    UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add_role")
    @ResponseBody
    public ResponseEntity<?> addRolesToUser(@Valid @RequestBody RoleChangeRequest roleChangeRequest) {
        String email = roleChangeRequest.getUsername();
        List<String> newRolesList = roleChangeRequest.getNewRolesNames();
        try {
            List<String> currentRolesList = userService.addRolesToUser(email, newRolesList);
            return ResponseEntity.ok(new RoleChangeResponse(
                    "Roles added successfully", currentRolesList
            ));
        }
        catch (AssertionError e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("One of roles is already in list");
        }
    }
}
