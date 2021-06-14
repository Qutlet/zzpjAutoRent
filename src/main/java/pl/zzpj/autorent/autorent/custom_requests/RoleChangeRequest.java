package pl.zzpj.autorent.autorent.custom_requests;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

public class RoleChangeRequest {
    @Getter @Setter private List<String> newRolesNames;

    @NotBlank
    @Getter @Setter private String username;
}
