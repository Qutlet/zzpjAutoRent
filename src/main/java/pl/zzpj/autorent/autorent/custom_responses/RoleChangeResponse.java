package pl.zzpj.autorent.autorent.custom_responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
public class RoleChangeResponse {
    @Getter @Setter String message;
    @Getter @Setter List<String> roles;
}
