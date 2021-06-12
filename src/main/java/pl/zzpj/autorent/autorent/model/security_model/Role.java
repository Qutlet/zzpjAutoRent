package pl.zzpj.autorent.autorent.model.security_model;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

@AllArgsConstructor
public class Role implements GrantedAuthority {
    private final String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
