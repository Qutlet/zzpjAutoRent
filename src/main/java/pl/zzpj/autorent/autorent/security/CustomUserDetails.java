package pl.zzpj.autorent.autorent.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pl.zzpj.autorent.autorent.model.User;
import pl.zzpj.autorent.autorent.model.security_model.Role;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CustomUserDetails implements UserDetails {
    User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        final Set<GrantedAuthority> result = new HashSet<>();
        final List<String> rolesNames = this.user.getRolesNames();
        if(rolesNames.size() > 0) {
            for (String roleName : rolesNames) {
                result.add(new Role(roleName));
            }
        }
        else {
            result.add(new Role("USER"));
        }

        return result;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
