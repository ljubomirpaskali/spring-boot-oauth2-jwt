package sample.config;

import java.util.Collections;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import sample.model.User;
import sample.service.UserService;

@Component
public class SampleAuthenticationProvider implements AuthenticationProvider {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public SampleAuthenticationProvider(UserService userService, BCryptPasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        Supplier<RuntimeException> invalidCredentials = () -> new RuntimeException("Invalid credentials");

        String username = Optional.ofNullable(authentication.getPrincipal())
                .map(Object::toString).orElseThrow(invalidCredentials);

        String password = Optional.ofNullable(authentication.getCredentials())
                .map(Object::toString).orElseThrow(invalidCredentials);

        User user = userService.findByUsername(username);

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw invalidCredentials.get();
        }

        return new UsernamePasswordAuthenticationToken(user.getUsername(), null,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }

}
