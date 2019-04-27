package sample.service;

import java.util.List;
import java.util.Optional;

import sample.model.User;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    Optional<User> findByUsername(String username);

}
