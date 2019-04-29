package sample.service;

import java.util.List;

import sample.model.User;

public interface UserService {

    List<User> findAll();

    User findById(Long id);

    User findByUsername(String username);

}
