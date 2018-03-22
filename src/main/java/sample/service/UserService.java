package sample.service;

import sample.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    Optional<User> findByUsername(String username);

}
