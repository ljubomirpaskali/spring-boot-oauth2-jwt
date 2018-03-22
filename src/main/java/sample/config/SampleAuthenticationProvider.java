package sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import sample.model.User;
import sample.service.UserService;

import java.util.Arrays;
import java.util.Optional;

@Component
public class SampleAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = Optional.ofNullable(authentication.getPrincipal())
                .map(Object::toString).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        String password = Optional.ofNullable(authentication.getCredentials())
                .map(Object::toString).orElseThrow(() -> new RuntimeException("Invalid credentials"));

        Optional<User> user = userService.findByUsername(username);

        if (!user.isPresent()) {
            throw new RuntimeException("Invalid credentials");
        }

        if (!passwordEncoder.matches(password, user.get().getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return new UsernamePasswordAuthenticationToken(user.get().getUsername(), null,
                Arrays.asList(new SimpleGrantedAuthority(user.get().getRole())));
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
